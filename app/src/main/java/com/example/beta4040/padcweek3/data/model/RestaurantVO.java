package com.example.beta4040.padcweek3.data.model;

/**
 * Created by beta4040 on 6/23/17.
 */
import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RestaurantVO implements Serializable
{

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("addr-short")
    @Expose
    private String addrShort;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("total-rating-count")
    @Expose
    private Integer totalRatingCount;
    @SerializedName("average-rating-value")
    @Expose
    private Float averageRatingValue;
    @SerializedName("is-ad")
    @Expose
    private Boolean isAd;
    @SerializedName("is-new")
    @Expose
    private Boolean isNew;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("lead-time-in-min")
    @Expose
    private Integer leadTimeInMin;
    private final static long serialVersionUID = -5271147397991539851L;

    /**
     * No args constructor for use in serialization
     *
     */
    public RestaurantVO() {
    }

    /**
     *
     * @param tags
     * @param title
     * @param addrShort
     * @param isAd
     * @param averageRatingValue
     * @param isNew
     * @param image
     * @param leadTimeInMin
     * @param totalRatingCount
     */
    public RestaurantVO(String title, String addrShort, String image, Integer totalRatingCount, Float averageRatingValue, Boolean isAd, Boolean isNew, List<String> tags, Integer leadTimeInMin) {
        super();
        this.title = title;
        this.addrShort = addrShort;
        this.image = image;
        this.totalRatingCount = totalRatingCount;
        this.averageRatingValue = averageRatingValue;
        this.isAd = isAd;
        this.isNew = isNew;
        this.tags = tags;
        this.leadTimeInMin = leadTimeInMin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddrShort() {
        return addrShort;
    }

    public void setAddrShort(String addrShort) {
        this.addrShort = addrShort;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(Integer totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public Float getAverageRatingValue() {
        return averageRatingValue;
    }

    public void setAverageRatingValue(Float averageRatingValue) {
        this.averageRatingValue = averageRatingValue;
    }

    public Boolean getIsAd() {
        return isAd;
    }

    public void setIsAd(Boolean isAd) {
        this.isAd = isAd;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getLeadTimeInMin() {
        return leadTimeInMin;
    }

    public void setLeadTimeInMin(Integer leadTimeInMin) {
        this.leadTimeInMin = leadTimeInMin;
    }

}


