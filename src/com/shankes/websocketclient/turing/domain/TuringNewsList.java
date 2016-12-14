package com.shankes.websocketclient.turing.domain;

/**
 * 信息列表(新闻标题,新闻来源,新闻图片,新闻详情链接)
 * 
 * @author shankes
 */
public class TuringNewsList {

	private String article;// 新闻标题
	private String source;// 新闻来源
	private String icon;// 新闻图片
	private String detailurl;// 新闻详情链接

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDetailurl() {
		return detailurl;
	}

	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
}
