package com.plugin.jira.api.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.POIXMLException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.plugin.jira.api.domain.Issue;
import com.plugin.jira.api.mapper.RowMappable;
import com.plugin.jira.api.util.ExcelUtils;
import com.plugin.jira.api.util.GenericUtils;

/**
 * It will extract the data and produce the set of issue from excel file
 * 
 * @author vishal.zanzrukia
 * 
 */
public class ExcelToIssueDataExtractor implements DataExtractor<Issue> {

  public Set<Issue> readData(ExcelConfig config) {

    Set<Issue> issues = new LinkedHashSet<>();

    /** Initializing the necessary resources for reading data from */
    try (InputStream file = new FileInputStream(new File(config.getInputFilePath()));
        Workbook workbook = new XSSFWorkbook(file);) {

      /** Get the sheet from the workbook */
      Sheet sheet = null;
      if (config.getInputSheetNumber() != null) {
        sheet = workbook.getSheetAt(config.getInputSheetNumber() - 1);
      } else {
        sheet = workbook.getSheet(config.getInputSheetName());
      }

      /** Iterate through each rows from first sheet */
      Iterator<Row> rowIterator = sheet.iterator();
      boolean isHeaderEvaluated = false;
      Row row = null;
      int continueEmptyRowCounter = 0;

      while (rowIterator.hasNext()) {

        row = rowIterator.next();

        /** evaluating header */
        if (!isHeaderEvaluated && config.isHeader()) {
          isHeaderEvaluated = true;
          continue;
        }

        /** evaluating empty row */
        if (ExcelUtils.isRowEmpty(row)) {
          continueEmptyRowCounter++;
        } else {
          RowMappable<Issue> mapper = config.getExcelColumn().getMapper();
          issues.add(mapper.mapRow(row));
        }

        if (continueEmptyRowCounter > config.getContinueEmptyRowThresold()) {
          // TODO log appropriate message
          break;
        }
      }
    } catch (POIXMLException | IOException e) {
      if (e instanceof POIXMLException && GenericUtils.isStringValid(e.getMessage())
          && e.getMessage().indexOf("57699") != -1) {
        // TODO replace sysout
        System.err.println("Your input file is not valid excel file. Please check it");
      } else {
        // TODO replace sysout
        e.printStackTrace();
      }
    }
    return issues;
  }
}
