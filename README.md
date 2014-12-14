datepicker4j
============
Date picker for java swing

###Maven

```xml
<dependency>
  <groupId>io.loli</groupId>
  <artifactId>datepicker4j</artifactId>
  <version>0.0.3</version>
</dependency>
```

###Usage

```java
JTextField field = new JTextField();
DatePicker.datePicker(field);
// DatePicker.datePicker(field, "yyyy-MM-dd");
```

###Example

####Basic Example
```java
JFrame frame = new JFrame();
frame.setVisible(true);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
JTextField field = new JTextField();
DatePicker.datePicker(field);
frame.add(field);
frame.pack();
```

####Make SUN&SAT unclickable
```
DatePicker.datePicker(field, "yyyy-MM-dd", new ClickableDateFilter() {
    public boolean filter(Date date) {
        return date.getDay() == 0 || date.getDay() == 6;
    }
});
```


###Screenshot
![DEMO](http://r.loli.io/ZfiARn.png)
