package wiki.gak.graduation.components;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;
import org.apache.commons.lang3.StringUtils;

/**
 * 列表整数转换
 *
 * @author BugRui
 * @date 2020/5/8 23:46
 **/
public class AxisConverter implements AttributeConverter<List<List<Integer>>, String> {

  @Override
  public String convertToDatabaseColumn(List<List<Integer>> attribute) {
    StringBuilder stringBuilder = new StringBuilder();
    for (List<Integer> axisX :
        attribute) {
      stringBuilder.append(StringUtils.join(axisX, ",")).append(";");
    }
    return stringBuilder.toString();
  }

  @Override
  public List<List<Integer>> convertToEntityAttribute(String dbData) {
    List<List<Integer>> list = new ArrayList<>();
    String[] axisX = dbData.split(";");
    for (String x : axisX) {
      String[] axisY = x.split(",");
      ArrayList<Integer> yList = new ArrayList<>();
      for (String y : axisY) {
        yList.add(Integer.parseInt(y));
      }
      list.add(yList);
    }
    return list;
  }
}
