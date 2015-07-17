package com.plugin.jira.api.excel;

/**
 * It will provide the configuration related excel file while will be used while extracting data
 * from it.
 * 
 * @author vishal.zanzrukia
 * 
 */
public abstract class SimpleExcelConfig implements ExcelConfig {

  private Integer inputSheetNumber;
  private String inputSheetName;

  public SimpleExcelConfig(Integer inputSheetNumber) {
    this.inputSheetNumber = inputSheetNumber;
  }

  public SimpleExcelConfig(String inputSheetName) {
    this.inputSheetName = inputSheetName;
  }

  @Override
  public Integer getPrimaryColumn() {
    return 0;
  }

  public Integer getInputSheetNumber() {
    return inputSheetNumber;
  }

  public String getInputSheetName() {
    return inputSheetName;
  }

  @Override
  public Boolean isHeader() {
    return Boolean.TRUE;
  }

  @Override
  public Integer getContinueEmptyRowThresold() {
    return 10;
  }
  
  @Override
  public ImportPolicy getImportPolicy() {
    return ImportPolicy.MERGE;
  }

  @Override
  public String toString() {
    StringBuffer buffer = new StringBuffer("[Sheet No : ");
    buffer.append(inputSheetNumber);
    buffer.append(", Sheet Name :");
    buffer.append(inputSheetName);
    buffer.append(", Primary Column Index : ");
    buffer.append(getPrimaryColumn());
    buffer.append(", isHeader : ");
    buffer.append(isHeader());
    buffer.append(", Continue Empty Row Thresold : ");
    buffer.append(getContinueEmptyRowThresold());
    buffer.append(", Column Indexing Mapping : ");
    buffer.append(getExcelColumn());
    buffer.append("]");
    return buffer.toString();
  }
}
