/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.properties;



/**
 * Created by hansolo on 24.10.17.
 */
public class Demo {
    private PoJo                   pojo;
    private DoubleProperty         doubleProperty;
    private ObjectProperty<String> objectProperty;
    private IntegerProperty        integerProperty;
    private DoubleProperty         doubleProperty1;
    private ReadOnlyDoubleProperty readOnlyDoubleProperty;


    public Demo() {
        pojo = new PoJo();

        // Setup properties
        doubleProperty = new DoubleProperty();

        objectProperty = new ObjectProperty();

        integerProperty = new IntegerProperty(10) {
            @Override public void set(final int VALUE) { super.set(VALUE); }
            @Override public int get() { return super.get(); }
            @Override protected void invalidated() { System.out.println("Color changed to: " + get()); }
            @Override public Object getBean() { return Demo.this; }
            @Override public String getName() { return "color"; }
        };

        doubleProperty1 = new DoubleProperty(Demo.this, "oldValue", 10);

        readOnlyDoubleProperty = new ReadOnlyDoubleProperty(5);


        // Register listeners
        pojo.doubleValueProperty().setOnPropertyChanged(e -> System.out.println(e.getOldValue() + " -> " + e.getValue()));

        doubleProperty.addListener(e -> System.out.println(e.getOldValue() + " -> " + e.getValue()));

        objectProperty.addListener(e -> System.out.println(e.getOldValue() + " -> " + e.getValue()));


        // Set values
        pojo.setDoubleValue(7);

        doubleProperty.set(20);

        objectProperty.set(new String("Hallo"));

        objectProperty.set(new String("Test"));
    }

    public static void main(String[] args) {
        new Demo();
    }
}
