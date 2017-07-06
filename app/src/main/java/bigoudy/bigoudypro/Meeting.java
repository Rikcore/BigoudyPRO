package bigoudy.bigoudypro;

/**
 * Created by M.C on 06/06/2017.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meeting implements Serializable {

    @SerializedName("idMeeting")
    @Expose
    private String idMeeting;
    @SerializedName("titleMeeting")
    @Expose
    private Object titleMeeting;
    @SerializedName("descriptionMeeting")
    @Expose
    private Object descriptionMeeting;
    @SerializedName("durationMeeting")
    @Expose
    private Object durationMeeting;
    @SerializedName("addressMeeting")
    @Expose
    private String addressMeeting;
    @SerializedName("cityMeeting")
    @Expose
    private String cityMeeting;
    @SerializedName("zipcodeMeeting")
    @Expose
    private String zipcodeMeeting;
    @SerializedName("completeAddressMeeting")
    @Expose
    private Object completeAddressMeeting;
    @SerializedName("dateCreationMeeting")
    @Expose
    private String dateCreationMeeting;
    @SerializedName("ammountBaseHT")
    @Expose
    private String ammountBaseHT;
    @SerializedName("ammountWithDiscountHT")
    @Expose
    private String ammountWithDiscountHT;
    @SerializedName("discountPercentMeeting")
    @Expose
    private String discountPercentMeeting;
    @SerializedName("timeIncreasePriceMeeting")
    @Expose
    private String timeIncreasePriceMeeting;
    @SerializedName("ammountWithTimeIncreaseHT")
    @Expose
    private String ammountWithTimeIncreaseHT;
    @SerializedName("paymentIdMeeting")
    @Expose
    private Object paymentIdMeeting;
    @SerializedName("nbPersMeeting")
    @Expose
    private String nbPersMeeting;
    @SerializedName("moreInfoAskMeeting")
    @Expose
    private Object moreInfoAskMeeting;
    @SerializedName("moreInfoBigouder")
    @Expose
    private Object moreInfoBigouder;
    @SerializedName("isADemandMeeting")
    @Expose
    private String isADemandMeeting;
    @SerializedName("idCustomer")
    @Expose
    private String idCustomer;
    @SerializedName("firstnameCustomer")
    @Expose
    private String firstnameCustomer;
    @SerializedName("lastnameCustomer")
    @Expose
    private String lastnameCustomer;
    @SerializedName("ageUser")
    @Expose
    private Object ageUser;
    @SerializedName("idStripeCustomer")
    @Expose
    private Object idStripeCustomer;
    @SerializedName("bigouderPriceModification")
    @Expose
    private Object bigouderPriceModification;
    @SerializedName("linkAvatarCustomer")
    @Expose
    private String linkAvatarCustomer;
    @SerializedName("telCustomer")
    @Expose
    private String telCustomer;
    @SerializedName("dateMeeting")
    @Expose
    private String dateMeeting;
    @SerializedName("beginTimeAvailable")
    @Expose
    private String beginTimeAvailable;
    @SerializedName("isOverMeeting")
    @Expose
    private String isOverMeeting;
    @SerializedName("onlinePayment")
    @Expose
    private String onlinePayment;
    @SerializedName("dateMeetingDiagnostic")
    @Expose
    private Object dateMeetingDiagnostic;
    @SerializedName("beginTimeAvailableDiagnostic")
    @Expose
    private Object beginTimeAvailableDiagnostic;
    @SerializedName("performances")
    @Expose
    private List<Performance> performances = new ArrayList<Performance>();
    @SerializedName("diagnosticResponses")
    @Expose
    private List<Object> diagnosticResponses = new ArrayList<Object>();
    @SerializedName("diagnosticPhotos")
    @Expose
    private List<DiagnosticPhoto> diagnosticPhotos = new ArrayList<DiagnosticPhoto>();

    public String getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(String idMeeting) {
        this.idMeeting = idMeeting;
    }

    public Object getTitleMeeting() {
        return titleMeeting;
    }

    public void setTitleMeeting(Object titleMeeting) {
        this.titleMeeting = titleMeeting;
    }

    public Object getDescriptionMeeting() {
        return descriptionMeeting;
    }

    public void setDescriptionMeeting(Object descriptionMeeting) {
        this.descriptionMeeting = descriptionMeeting;
    }

    public Object getDurationMeeting() {
        return durationMeeting;
    }

    public void setDurationMeeting(Object durationMeeting) {
        this.durationMeeting = durationMeeting;
    }

    public String getAddressMeeting() {
        return addressMeeting;
    }

    public void setAddressMeeting(String addressMeeting) {
        this.addressMeeting = addressMeeting;
    }

    public String getCityMeeting() {
        return cityMeeting;
    }

    public void setCityMeeting(String cityMeeting) {
        this.cityMeeting = cityMeeting;
    }

    public String getZipcodeMeeting() {
        return zipcodeMeeting;
    }

    public void setZipcodeMeeting(String zipcodeMeeting) {
        this.zipcodeMeeting = zipcodeMeeting;
    }

    public Object getCompleteAddressMeeting() {
        return completeAddressMeeting;
    }

    public void setCompleteAddressMeeting(Object completeAddressMeeting) {
        this.completeAddressMeeting = completeAddressMeeting;
    }

    public String getDateCreationMeeting() {
        return dateCreationMeeting;
    }

    public void setDateCreationMeeting(String dateCreationMeeting) {
        this.dateCreationMeeting = dateCreationMeeting;
    }

    public String getAmmountBaseHT() {
        return ammountBaseHT;
    }

    public void setAmmountBaseHT(String ammountBaseHT) {
        this.ammountBaseHT = ammountBaseHT;
    }

    public String getAmmountWithDiscountHT() {
        return ammountWithDiscountHT;
    }

    public void setAmmountWithDiscountHT(String ammountWithDiscountHT) {
        this.ammountWithDiscountHT = ammountWithDiscountHT;
    }

    public String getDiscountPercentMeeting() {
        return discountPercentMeeting;
    }

    public void setDiscountPercentMeeting(String discountPercentMeeting) {
        this.discountPercentMeeting = discountPercentMeeting;
    }

    public String getTimeIncreasePriceMeeting() {
        return timeIncreasePriceMeeting;
    }

    public void setTimeIncreasePriceMeeting(String timeIncreasePriceMeeting) {
        this.timeIncreasePriceMeeting = timeIncreasePriceMeeting;
    }

    public String getAmmountWithTimeIncreaseHT() {
        return ammountWithTimeIncreaseHT;
    }

    public void setAmmountWithTimeIncreaseHT(String ammountWithTimeIncreaseHT) {
        this.ammountWithTimeIncreaseHT = ammountWithTimeIncreaseHT;
    }

    public Object getPaymentIdMeeting() {
        return paymentIdMeeting;
    }

    public void setPaymentIdMeeting(Object paymentIdMeeting) {
        this.paymentIdMeeting = paymentIdMeeting;
    }

    public String getNbPersMeeting() {
        return nbPersMeeting;
    }

    public void setNbPersMeeting(String nbPersMeeting) {
        this.nbPersMeeting = nbPersMeeting;
    }

    public Object getMoreInfoAskMeeting() {
        return moreInfoAskMeeting;
    }

    public void setMoreInfoAskMeeting(Object moreInfoAskMeeting) {
        this.moreInfoAskMeeting = moreInfoAskMeeting;
    }

    public Object getMoreInfoBigouder() {
        return moreInfoBigouder;
    }

    public void setMoreInfoBigouder(Object moreInfoBigouder) {
        this.moreInfoBigouder = moreInfoBigouder;
    }

    public String getIsADemandMeeting() {
        return isADemandMeeting;
    }

    public void setIsADemandMeeting(String isADemandMeeting) {
        this.isADemandMeeting = isADemandMeeting;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstnameCustomer() {
        return firstnameCustomer;
    }

    public void setFirstnameCustomer(String firstnameCustomer) {
        this.firstnameCustomer = firstnameCustomer;
    }

    public String getLastnameCustomer() {
        return lastnameCustomer;
    }

    public void setLastnameCustomer(String lastnameCustomer) {
        this.lastnameCustomer = lastnameCustomer;
    }

    public Object getAgeUser() {
        return ageUser;
    }

    public void setAgeUser(Object ageUser) {
        this.ageUser = ageUser;
    }

    public Object getIdStripeCustomer() {
        return idStripeCustomer;
    }

    public void setIdStripeCustomer(Object idStripeCustomer) {
        this.idStripeCustomer = idStripeCustomer;
    }

    public Object getBigouderPriceModification() {
        return bigouderPriceModification;
    }

    public void setBigouderPriceModification(Object bigouderPriceModification) {
        this.bigouderPriceModification = bigouderPriceModification;
    }

    public String getLinkAvatarCustomer() {
        return linkAvatarCustomer;
    }

    public void setLinkAvatarCustomer(String linkAvatarCustomer) {
        this.linkAvatarCustomer = linkAvatarCustomer;
    }

    public String getTelCustomer() {
        return telCustomer;
    }

    public void setTelCustomer(String telCustomer) {
        this.telCustomer = telCustomer;
    }

    public String getDateMeeting() {
        return dateMeeting;
    }

    public void setDateMeeting(String dateMeeting) {
        this.dateMeeting = dateMeeting;
    }

    public String getBeginTimeAvailable() {
        return beginTimeAvailable;
    }

    public void setBeginTimeAvailable(String beginTimeAvailable) {
        this.beginTimeAvailable = beginTimeAvailable;
    }

    public String getIsOverMeeting() {
        return isOverMeeting;
    }

    public void setIsOverMeeting(String isOverMeeting) {
        this.isOverMeeting = isOverMeeting;
    }

    public String getOnlinePayment() {
        return onlinePayment;
    }

    public void setOnlinePayment(String onlinePayment) {
        this.onlinePayment = onlinePayment;
    }

    public Object getDateMeetingDiagnostic() {
        return dateMeetingDiagnostic;
    }

    public void setDateMeetingDiagnostic(Object dateMeetingDiagnostic) {
        this.dateMeetingDiagnostic = dateMeetingDiagnostic;
    }

    public Object getBeginTimeAvailableDiagnostic() {
        return beginTimeAvailableDiagnostic;
    }

    public void setBeginTimeAvailableDiagnostic(Object beginTimeAvailableDiagnostic) {
        this.beginTimeAvailableDiagnostic = beginTimeAvailableDiagnostic;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public List<Object> getDiagnosticResponses() {
        return diagnosticResponses;
    }

    public void setDiagnosticResponses(List<Object> diagnosticResponses) {
        this.diagnosticResponses = diagnosticResponses;
    }

    public List<DiagnosticPhoto> getDiagnosticPhotos() {
        return diagnosticPhotos;
    }

    public void setDiagnosticPhotos(List<DiagnosticPhoto> diagnosticPhotos) {
        this.diagnosticPhotos = diagnosticPhotos;
    }

    public WeekViewEvent getEvent (String dateMeeting, String beginTimeAvailable, int newMonth, int newYear, BookingModel bookingModel, int position, int duration){

        WeekViewEvent event;

        String[] hourSplit = beginTimeAvailable.split(":");
        int hour = Integer.valueOf(hourSplit[0]);
        int minute = Integer.valueOf(hourSplit[1]);

        String[] dateSplit = dateMeeting.split("-");
        int year = Integer.valueOf(dateSplit[0]);
        int month = Integer.valueOf(dateSplit[1]);
        int day = Integer.valueOf(dateSplit[2]);

        if (month == newMonth && year == newYear) {
            Calendar starTime = Calendar.getInstance();
            starTime.set(Calendar.HOUR_OF_DAY, hour);
            starTime.set(Calendar.MINUTE, minute);
            starTime.set(Calendar.DAY_OF_MONTH, day);
            starTime.set(Calendar.MONTH, month - 1);
            starTime.set(Calendar.YEAR, year);
            Calendar endtime = (Calendar) starTime.clone();
            endtime.add(Calendar.MINUTE, duration);
            Integer idMeeting = Integer.valueOf(bookingModel.getMeetings().get(position).getIdMeeting());
            String customerFirstName = bookingModel.getMeetings().get(position).getFirstnameCustomer();
            String perf = bookingModel.getMeetings().get(position).getPerformances().get(0).getLibPerformance();
            String rdv = customerFirstName+" "+perf;
            event = new WeekViewEvent(idMeeting, rdv, starTime, endtime);
            return event;


        } else {
            return null;

        }



    }


}
