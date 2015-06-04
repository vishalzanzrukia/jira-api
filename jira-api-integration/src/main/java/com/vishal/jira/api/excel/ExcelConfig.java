package com.vishal.jira.api.excel;


/**
 * It will define all the configuration related excel file while will be used while extracting data
 * from excel.<BR>
 * <STRONG>NOTE:</STRONG> Please make sure that you are not using any cell merging while importing
 * the excel file.
 * 
 * @author vishal.zanzrukia
 * 
 */
public interface ExcelConfig {

  /**
   * It defines the excel import policy
   * 
   * @author vishal.zanzrukia
   * 
   */
  enum ImportPolicy {
    /**
     * It will only create new issues, will not check for existing issues at all.
     */
    ONLY_CREATE,
    /**
     * It will create issue only if your matching criteria does not match any of issues for given
     * project, otherwise update it.
     */
    MERGE
  }

  /**
   * It must provide the full file path of input excel file
   * 
   * @return
   */
  String getInputFilePath();

  /**
   * This is primary column <STRONG>index</STRONG> which will be used to check ignore whole row, in
   * case primary column value is empty. <BR>
   * If you do not need such configuration, simply do not override it, api will consider 0 index by
   * default<BR>
   * <STRONG>NOTE : </STRONG> index starts from 0.
   * 
   * @see SimpleExcelConfig
   * @return
   */
  Integer getPrimaryColumn();

  /**
   * This must provide the number of sheet in which data has been uploaded.
   * 
   * @return
   */
  Integer getInputSheetNumber();

  /**
   * This must provide the name of sheet in which data has been uploaded.
   * 
   * @return
   */
  String getInputSheetName();

  /**
   * It will provide the mapper class for the issue.
   * 
   * @return
   */
  ExcelColumnIndexMapper getExcelColumn();

  /**
   * It will provide whether your excel sheet contains header or not.<BR>
   * <STRONG>NOTE:</STRONG> By default value : true.
   * 
   * @return
   */
  Boolean isHeader();

  /**
   * It will provide number by which api will decide to ignore the all above rows
   * <STRONG>NOTE:</STRONG> By default value : 10.
   * 
   * @return
   */
  Integer getContinueEmptyRowThresold();
  
  /**
   * It will provide which import policy should be while creating/updating issues.
   *<STRONG>NOTE:</STRONG> By default value : {@link ImportPolicy#MERGE}
   *
   * @see ImportPolicy
   * @return
   */
  ImportPolicy getImportPolicy();
}
