package com.yang.testdemo.bean;

/**
 * 答案
 * Created by yangle on 2017/9/5.
 */

public class Answer {

    public Scene scene;
    public String idx;
    public String sig;

    public class Scene {
        public Ques ques;
        public String idx;
        public String type;

        public class Ques {
            public Opt opt;
            public String idx;

            public class Opt {
                public I i;

                public class I {
                    public String idx;
                    public String ans;
                    public String r;

                    @Override
                    public String toString() {
                        return "I{" +
                                "idx='" + idx + '\'' +
                                ", ans='" + ans + '\'' +
                                ", r='" + r + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "Opt{" +
                            "i=" + i +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "Ques{" +
                        "opt=" + opt +
                        ", idx='" + idx + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Scene{" +
                    "ques=" + ques +
                    ", idx='" + idx + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Answer{" +
                "scene=" + scene +
                ", idx='" + idx + '\'' +
                ", sig='" + sig + '\'' +
                '}';
    }
}
