package com.licyun.blog.core.lucene;

import java.util.Arrays;
import java.util.Collection;

import com.licyun.blog.core.plugin.MapContainer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;

public class DocConverter{

  private DocConverter(){
  }

  public static MapContainer convert(Document obj){
    MapContainer mc = new MapContainer();
    for(IndexableField field : obj.getFields()){
      mc.put(field.name(), field.stringValue());
    }

    return mc;
  }

  public static MapContainer convert(Document obj, String... filters){
    return convert(obj, Arrays.asList(filters));
  }

  public static MapContainer convert(Document obj, Collection<String> filters){
    MapContainer mc = new MapContainer();
    for(IndexableField field : obj.getFields()){
      if(filters.contains(field.name()))
        continue;
      mc.put(field.name(), field.stringValue());
    }

    return mc;
  }

}
