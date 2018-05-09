package com.example.brvah;

import java.util.List;

/**
 * Created by Administrator
 * on 2018/5/8 0008.
 * at 北京
 */

public class DataBean {

    /**
     * name : 苏州园林
     * t_id : 1
     * content : 苏州园林，又称苏州古典园林。是指苏州城内，以私家园林为主的传统汉族建筑，可谓“咫尺之内再造乾坤”，是中国园林和江南园林的翘（qíao）楚和骄傲。
     苏州园林其历史可追溯至春秋时期吴王的园囿（yòu），形成于五代，成熟于赵宋，鼎盛于清代。清末时苏州已有各色园林一百七十多处，现保存完整的有六十余处，对外开放的园林有十九处。
     苏州园林典型例证的拙政园、留园、网师园和环秀山庄等，诞生于苏州私家园林发展的鼎盛时期，以其意境深远、构筑精致、艺术高雅、文化内涵丰富而成为苏州众多古典园林的典范与代表2010年苏州园林景区成为国家5A级旅游景区。
     * imgs : ["http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg","http://img.juhe.cn/joke/201412/19/62287B57ED97B8A06861ADA51D921CEB.jpg","http://img.juhe.cn/joke/201412/19/E3070767518CB4DFEA708DCCC332EE2F.jpg"]
     */

    private String name;
    private int t_id;
    private String content;
    private List<String> imgs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
