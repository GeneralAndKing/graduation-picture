package wiki.gak.graduation.components;

import com.qiniu.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeConverter;

/**
 * @author BugRui
 * @date 2020/5/8 23:46
 **/
public class ListIntegerConverter implements AttributeConverter<List<Integer>, String> {


  @Override
  public String convertToDatabaseColumn(List<Integer> attribute) {
    return StringUtils.join(attribute, ",");
  }

  @Override
  public List<Integer> convertToEntityAttribute(String dbData) {
    String[] arr = dbData.split(",");
    List<Integer> list = new ArrayList<>();
    for (String s : arr) {
      list.add(Integer.parseInt(s));
    }
    return list;
  }
}
