package org.qingqiao.jdbc;

public class FenYe {
    private Integer homepage;//起始位置
    private Integer onpage;//上一页
    private Integer nextpage;//下一页
    private Integer allpage;//数据总数
    private Integer lastpage;//尾页
    private Integer nowoage;//当前页
    private Integer pagecount;//分业单位3

    public Integer getHomepage() {
        return homepage;
    }

    public Integer getOnpage() {
        return onpage;
    }

    public Integer getNextpage() {
        return nextpage;
    }

    public Integer getAllpage() {
        return allpage;
    }

    public Integer getLastpage() {
        return lastpage;
    }

    public Integer getNowoage() {
        return nowoage;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public FenYe(String nowoage, Integer allpage, Integer pagecount){
        if (nowoage==null){
            this.nowoage=1;
        }else{
            this.nowoage = Integer.parseInt(nowoage);
        }
        this.pagecount=pagecount;
        this.allpage=allpage;
        op_lastpage();
        op_onpage();
        op_nextpage();
        op_homepage();

    }
    public void op_onpage(){
        this.onpage = nowoage - 1 == 0?1:nowoage-1;
    }
    public void op_nextpage(){
        this.nextpage = nowoage + 1 >= lastpage?lastpage:nowoage + 1;
    }
    public void op_homepage(){
        this.homepage = 1;
    }

    public void op_lastpage(){
        this.lastpage = allpage % this.pagecount == 0 ?allpage / this.pagecount:allpage / this.pagecount + 1;
    }











}
