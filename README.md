datepicker4j
============
Date picker for java swing

###Maven

```xml
<dependency>
  <groupId>io.loli</groupId>
  <artifactId>datepicker4j</artifactId>
  <version>0.0.5</version>
</dependency>
```

###Usage

```java
JTextField field = new JTextField();
DatePicker.datePicker(field);
// DatePicker.datePicker(field, "yyyy-MM-dd");
```

###Example

####Date Picker

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

###Time Picker

```
JTextField field = new JTextField();
DatePicker.timePicker(field);
// DatePicker.timePicker(field, "HH:mm");
```

###Datetime Picker

```
JTextField field = new JTextField();
DatePicker.dateTimePicker(field);
// DatePicker.timePicker(field, "yyyy-MM-dd HH:mm");
```

###Screenshot
![DEMO1](http://r.loli.io/ZfiARn.png)


![DEMO2](http://r.loli.io/aq2qeu.png)


![DEMO3](http://r.loli.io/3aqEVf.png)