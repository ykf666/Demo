package com.code.demo.LoadClassString;

import java.io.IOException;
import java.util.Map;

/**
 * @author yan.kefei
 * @date 2018/6/1 17:42
 */
public class Demo {
    public static void main(String[] args) {
        try {
            JavaStringCompiler compiler = new JavaStringCompiler();
            Map<String, byte[]> results = compiler.compile("UserProxy.java", JAVA_SOURCE_CODE);
            Class<?> clazz = compiler.loadClass("com.code.demo.LoadClassString.UserProxy", results);
            // try instance:
            User user = (User) clazz.newInstance();
            user.setName("hello");
            System.out.println(user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static final String JAVA_SOURCE_CODE = "/* a single java source file */   "
            + "package com.code.demo.LoadClassString;                      "
            + "public class UserProxy extends User {                          "
            + "    private boolean _dirty = false;                            "
            + "    @Override                                                  "
            + "    public void setId(String id) {                             "
            + "        super.setId(id);                                       "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    @Override                                                  "
            + "    public void setName(String name) {                         "
            + "        super.setName(name);                                   "
            + "        setDirty(true);                                        "
            + "    }                                                          "
            + "    public void setDirty(boolean dirty) {                      "
            + "        this._dirty = dirty;                                   "
            + "    }                                                          "
            + "    public boolean isDirty() {                                 "
            + "        return this._dirty;                                    "
            + "    }                                                          "
            + "}                                                              ";
}
