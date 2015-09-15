package com.plugin.jira.api.temp;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtils;

import com.newsapp.boilerpipe.image.ImageExtractor;

import de.l3s.boilerpipe.BoilerpipeExtractor;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.CommonExtractors;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;
import de.l3s.boilerpipe.sax.HTMLHighlighter;

public class ExtractHTML {
  public static String main(String urlStr) throws Exception {

    // String urlStr =
    // "http://timesofindia.indiatimes.com/home/specials/2014-assembly-elections/jammu-kashmir-news/Hung-assembly-in-JK-Who-will-form-govt/articleshow/45613959.cms";
    URL url = new URL(urlStr);

    final HTMLDocument htmlDoc = HTMLFetcher.fetch(url);
    final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
    String title = doc.getTitle();

    // choose from a set of useful BoilerpipeExtractors...
    final BoilerpipeExtractor extractor = CommonExtractors.ARTICLE_EXTRACTOR;

    // This new flag is all that is required to include images in extracted HTML
    HTMLHighlighter hh = HTMLHighlighter.newExtractingInstance();

    String extractedHtml = hh.process(url, extractor);
    String content =
        extractedHtml.substring(extractedHtml.indexOf("<BODY>") + 6,
            extractedHtml.indexOf("</BODY>"));

    final ImageExtractor ie = ImageExtractor.INSTANCE;
    List<com.newsapp.boilerpipe.image.Image> images = ie.process(url, extractor);
    Collections.sort(images);
    String image = null;
    if (!images.isEmpty()) {
      image = images.get(0).getSrc();
      // System.out.println(image.toString());
    }

    String jsonOut = articleTextToJson(content.trim(), title.trim(), urlStr, image);

    return jsonOut;
    // System.out.println(jsonOut);

    /*
     * PrintWriter out = new PrintWriter("C:\\Users\\Ravi\\Desktop\\highlighted.html", "UTF-8");
     * out.println("<base href=\"" + url + "\" >");
     * out.println("<meta http-equiv=\"Content-Type\" content=\"text-html; charset=utf-8\" />");
     * out.println(extractedHtml); out.close();
     */



  }

  public static String articleTextToJson(String content, String title, String urlStr, String image)
      throws MalformedURLException {
    URL url = new URL(urlStr);

    return "{\n" + "\"domain\" : \"" + "\", \n" + "\"next_page_id\": \"" + "\", \n" + "\"url\": \""
        + url + "\", \n" + "\"short_url\": \"" + "\", \n" + "\"author\": \"" + "\", \n"
        + "\"excerpt\" : \"" + "\", \n" + "\"direction\" : \"" + "\", \n" + "\"word_count\" : \""
        + "\", \n" + "\"total_pages\" : \"" + "\", \n" + "\"content\": \"" + content + "\", \n"
        + "\"date_published\" : \"" + "\", \n" + "\"dek\" : \"" + "\", \n"
        + "\"lead_image_url\": \"" + image + "\", \n" + "\"title\": \"" + title + "\", \n"
        + "\"rendered_pages\" : \"" + "\" \n" + "}";
  }

  public static void main(String[] args) {
    /*
     * Set<SortPage> list = new HashSet<SortPage>(); list.add(new SortPage("april")); list.add(new
     * SortPage("january")); list.add(new SortPage("september")); list.add(new SortPage("october"));
     * list.add(new SortPage("april")); list.add(new SortPage("december")); list.add(new
     * SortPage("august"));
     * 
     * for(SortPage sp : list) { System.out.println(sp); }
     * 
     * 
     * List<SortPage> list2 = sortCollectionByMonth(list, "month");
     * 
     * System.out.println("-----------------------");
     * 
     * for(SortPage sp : list2) { System.out.println(sp); }
     */
    // testSortMapMonthWiseByKey();
    /*
     * int basketItemVOsExplodedSize = 10; BigDecimal discountCount = new BigDecimal(4.9);
     * System.out.println("discountCount::"+discountCount.intValue()); int temp =
     * basketItemVOsExplodedSize / discountCount.intValue(); System.out.println("temp::"+temp); int
     * index = basketItemVOsExplodedSize - temp;
     */
    List<String> list = new ArrayList<String>(Arrays.asList("s1", "s2", "s1"));
    list.removeAll(new ArrayList<String>(Arrays.asList("s1")));
    System.out.println(list);



  }

  /*
   * public static <T> List<? super T> consumeList(List<? extends T> collection){ return null; }
   * 
   * public static <T> List<? super T> consumeSet(Set<? extends T> collection){ return null; }
   * 
   * public static <T,V extends Set<T> & List<T>> List<? super T> consumeListAndSetButNotMap(V
   * collection){ return null; }
   */

  public static <T> void sortCollectionByMonth(List<? extends T> listToSort,
      final String propertyName) {

    Collections.sort(listToSort, new Comparator<T>() {

      public int compare(T t1, T t2) {
        String monthName1 = null;
        String monthName2 = null;
        try {
          monthName1 = (String) PropertyUtils.getProperty(t1, propertyName);
          monthName2 = (String) PropertyUtils.getProperty(t2, propertyName);
        } catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException("error while getting property : " + propertyName
              + " value from " + t1);
        }

        try {
          return MyConstants.MONTH_ENUM.valueOf(monthName1.toUpperCase()).compareTo(
              MyConstants.MONTH_ENUM.valueOf(monthName2.toUpperCase()));
        } catch (IllegalArgumentException e) {
          /** if month name is wrong, then return both objects are same */
          return 0;
        }
      }
    });
  }

  public static <T> List<T> sortCollectionByMonth(Set<? extends T> listToSort,
      final String propertyName) {

    List<T> list = new ArrayList<T>(listToSort);
    sortCollectionByMonth(list, propertyName);
    return list;
  }


  public static <T> Map<T, ? super Object> sortMapMonthWiseByKey(Map<T, ? extends Object> map,
      final String propertyName) {

    TreeMap<T, Object> treemap = new TreeMap<T, Object>(new Comparator<T>() {

      @Override
      public int compare(T t1, T t2) {
        String monthName1 = null;
        String monthName2 = null;
        try {
          monthName1 = (String) PropertyUtils.getProperty(t1, propertyName);
          monthName2 = (String) PropertyUtils.getProperty(t2, propertyName);
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          return MyConstants.MONTH_ENUM.valueOf(monthName1.toUpperCase()).compareTo(
              MyConstants.MONTH_ENUM.valueOf(monthName2.toUpperCase()));
        } catch (IllegalArgumentException e) {
          /** if month name is wrong, then return both objects are same */
          return 0;
        }
      }
    });

    treemap.putAll(map);
    return treemap;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static List sortMapMonthWiseByKey(Object listToSort, String propertyName) {
    return sortMapMonthWiseByKey((List) listToSort, propertyName);
  }


  public static <T> List<T> sortMapMonthWiseByKey(List<T> listToSort, final String propertyName) {

    Collections.sort(listToSort, new Comparator<T>() {

      @SuppressWarnings("rawtypes")
      @Override
      public int compare(T t1, T t2) {
        String monthName1 = null;
        String monthName2 = null;
        try {
          Object key1 = ((Map) t1).keySet().iterator().next();
          Object key2 = ((Map) t2).keySet().iterator().next();

          monthName1 = (String) PropertyUtils.getProperty(key1, propertyName);
          monthName2 = (String) PropertyUtils.getProperty(key2, propertyName);
        } catch (Exception e) {
          e.printStackTrace();
        }

        try {
          return MyConstants.MONTH_ENUM.valueOf(monthName1.toUpperCase()).compareTo(
              MyConstants.MONTH_ENUM.valueOf(monthName2.toUpperCase()));
        } catch (IllegalArgumentException e) {
          /** if month name is wrong, then return both objects are same */
          return 0;
        }
      }
    });

    return listToSort;
  }

}
