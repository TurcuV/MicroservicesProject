package com.microservices.product_service.pagegenerator.core;

import com.microservices.product_service.pagegenerator.annotations.AutoGenerateComponent;


import org.hibernate.type.internal.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class FormComponent extends HtmlComponent implements ComponentBuilderInterface {

  public FormComponent(AutoGenerateComponent annotation) {
    super(annotation);
    populateOpeningTag();
    populateComponentBody();
    populateClosingTag();
  }

  @Override
  public void populateOpeningTag() {
   setOpeningTag("<form id=\""+getComponentName()+"\" method=\"POST\" action=\"http://localhost:8080/api/product\" class=\"fv-plugins-bootstrap5-form-inline row row-cols-lg-auto g-3\">");
  }

  @Override
  public void populateClosingTag() {
    setClosingTag("""
                    <div class="col-12">
                      <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                  </form>""");
  }

  @Override
  public void populateComponentBody(){
   String element =  """
          <div class="col-4">
            <label>%s</label>
            <input type=%s class="form-control" name="productRequest.%s"/>
          </div>
          """;
    StringBuilder formFields = new StringBuilder();

    for(Field field : getParameterClass().getDeclaredFields()) {
//      String fieldType = field.isSubclassOf(field.getType(), Number.class) ? "number" : "text";
      formFields.append(String.format(element, field.getName(), "text", field.getName()));
      if (Collection.class.isAssignableFrom(field.getType())) {
//        try {
//          Field[] subFields = Class.forName(((ParameterizedTypeImpl) field.getGenericType()).getActualTypeArguments()[0].getTypeName()).getDeclaredFields();
//        } catch (ClassNotFoundException e) {
////        e.printStackTrace();
//        }
      }

    }

    setComponentBody(formFields.toString());
  }
}
