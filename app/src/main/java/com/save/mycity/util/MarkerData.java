package com.save.mycity.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by rishabhsingh.bisht on 13/02/16.
 */
public class MarkerData implements Parcelable{
  private long id;
  private int likeCount;
  private String title;
  private String discription;
  private String dateReported;
  private String dateClosed;

  public MarkerData(long id, String title, String discription, String dateClosed,
      String dateReported, int likeCount) {
    this.dateClosed = dateClosed;
    this.dateReported = dateReported;
    this.discription = discription;
    this.id = id;
    this.likeCount = likeCount;
    this.title = title;
  }

  protected MarkerData(Parcel in) {
    id = in.readLong();
    likeCount = in.readInt();
    title = in.readString();
    discription = in.readString();
    dateReported = in.readString();
    dateClosed = in.readString();
  }

  public static final Creator<MarkerData> CREATOR = new Creator<MarkerData>() {
    @Override public MarkerData createFromParcel(Parcel in) {
      return new MarkerData(in);
    }

    @Override public MarkerData[] newArray(int size) {
      return new MarkerData[size];
    }
  };

  public String getDateClosed() {
    return dateClosed;
  }
  public boolean isClosed(){
    return !TextUtils.isEmpty(dateClosed);
  }

  public void setDateClosed(String dateClosed) {
    this.dateClosed = dateClosed;
  }

  public String getDateReported() {
    return dateReported;
  }

  public void setDateReported(String dateReported) {
    this.dateReported = dateReported;
  }

  public String getDiscription() {
    return discription;
  }

  public void setDiscription(String discription) {
    this.discription = discription;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(int likeCount) {
    this.likeCount = likeCount;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(id);
    dest.writeInt(likeCount);
    dest.writeString(title);
    dest.writeString(discription);
    dest.writeString(dateReported);
    dest.writeString(dateClosed);
  }
}
