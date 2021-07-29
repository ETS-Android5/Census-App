package com.example.jpro;

import java.io.Serializable;

public class CitizenDataClass implements Serializable {

    //variables from this activity
   private String times="";
   private String medic="";
   private String visit="";
    private String enufullname="";

    //variables from previous activities
    private String notinschool="";

    //variables from previous activity
    private String readAndWrite="";
    private String everAttendedSch="";
    private String attendschlyear= "";
    private String highestgrade ="";

    //variables for data from previous activity
    private String gender="";
    private String marital_status="";
    private String contributeTohouseholdIncome = "";
    private  String beenaway = "";
    private  String relatedTohousehold = "";
    private  String ages="";
    private String names="";
    private String refnum="";

    //variables from previous activity
    private String currentlyinsch="";
    private   String currentgradeatt="";
    private String whoruns="";
    private String problemswithsch = "";


    //variables from previous activity
    private String livebirth="";
    private String prenatalcare="";
    private String postnatalcare = "";
    private String sicklast4wks = "";

    //variables from previous activity
    private String sortof;
    private String kindof="";
    private String sickness="";
    private String consultedlt4wks="";

    private String houseowner="";

    private String lati, loni;
    private String citizenimage;

    public CitizenDataClass() {

    }

    public CitizenDataClass(String times, String medic, String visit, String enufullname, String notinschool, String readAndWrite, String everAttendedSch, String attendschlyear, String highestgrade, String gender, String marital_status, String contributeTohouseholdIncome, String beenaway, String relatedTohousehold, String ages, String names, String refnum, String currentlyinsch, String currentgradeatt, String whoruns, String problemswithsch, String livebirth, String prenatalcare, String postnatalcare, String sicklast4wks, String sortof, String kindof, String sickness, String consultedlt4wks, String houseowner, String lati, String loni, String citizenimage) {
        this.times = times;
        this.medic = medic;
        this.visit = visit;
        this.enufullname = enufullname;
        this.notinschool = notinschool;
        this.readAndWrite = readAndWrite;
        this.everAttendedSch = everAttendedSch;
        this.attendschlyear = attendschlyear;
        this.highestgrade = highestgrade;
        this.gender = gender;
        this.marital_status = marital_status;
        this.contributeTohouseholdIncome = contributeTohouseholdIncome;
        this.beenaway = beenaway;
        this.relatedTohousehold = relatedTohousehold;
        this.ages = ages;
        this.names = names;
        this.refnum = refnum;
        this.currentlyinsch = currentlyinsch;
        this.currentgradeatt = currentgradeatt;
        this.whoruns = whoruns;
        this.problemswithsch = problemswithsch;
        this.livebirth = livebirth;
        this.prenatalcare = prenatalcare;
        this.postnatalcare = postnatalcare;
        this.sicklast4wks = sicklast4wks;
        this.sortof = sortof;
        this.kindof = kindof;
        this.sickness = sickness;
        this.consultedlt4wks = consultedlt4wks;
        this.houseowner = houseowner;
        this.lati = lati;
        this.loni = loni;
        this.citizenimage = citizenimage;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getMedic() {
        return medic;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

    public String getVisit() {
        return visit;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public String getEnufullname() {
        return enufullname;
    }

    public void setEnufullname(String enufullname) {
        this.enufullname = enufullname;
    }

    public String getNotinschool() {
        return notinschool;
    }

    public void setNotinschool(String notinschool) {
        this.notinschool = notinschool;
    }

    public String getReadAndWrite() {
        return readAndWrite;
    }

    public void setReadAndWrite(String readAndWrite) {
        this.readAndWrite = readAndWrite;
    }

    public String getEverAttendedSch() {
        return everAttendedSch;
    }

    public void setEverAttendedSch(String everAttendedSch) {
        this.everAttendedSch = everAttendedSch;
    }

    public String getAttendschlyear() {
        return attendschlyear;
    }

    public void setAttendschlyear(String attendschlyear) {
        this.attendschlyear = attendschlyear;
    }

    public String getHighestgrade() {
        return highestgrade;
    }

    public void setHighestgrade(String highestgrade) {
        this.highestgrade = highestgrade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getContributeTohouseholdIncome() {
        return contributeTohouseholdIncome;
    }

    public void setContributeTohouseholdIncome(String contributeTohouseholdIncome) {
        this.contributeTohouseholdIncome = contributeTohouseholdIncome;
    }

    public String getBeenaway() {
        return beenaway;
    }

    public void setBeenaway(String beenaway) {
        this.beenaway = beenaway;
    }

    public String getRelatedTohousehold() {
        return relatedTohousehold;
    }

    public void setRelatedTohousehold(String relatedTohousehold) {
        this.relatedTohousehold = relatedTohousehold;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getRefnum() {
        return refnum;
    }

    public void setRefnum(String refnum) {
        this.refnum = refnum;
    }

    public String getCurrentlyinsch() {
        return currentlyinsch;
    }

    public void setCurrentlyinsch(String currentlyinsch) {
        this.currentlyinsch = currentlyinsch;
    }

    public String getCurrentgradeatt() {
        return currentgradeatt;
    }

    public void setCurrentgradeatt(String currentgradeatt) {
        this.currentgradeatt = currentgradeatt;
    }

    public String getWhoruns() {
        return whoruns;
    }

    public void setWhoruns(String whoruns) {
        this.whoruns = whoruns;
    }

    public String getProblemswithsch() {
        return problemswithsch;
    }

    public void setProblemswithsch(String problemswithsch) {
        this.problemswithsch = problemswithsch;
    }

    public String getLivebirth() {
        return livebirth;
    }

    public void setLivebirth(String livebirth) {
        this.livebirth = livebirth;
    }

    public String getPrenatalcare() {
        return prenatalcare;
    }

    public void setPrenatalcare(String prenatalcare) {
        this.prenatalcare = prenatalcare;
    }

    public String getPostnatalcare() {
        return postnatalcare;
    }

    public void setPostnatalcare(String postnatalcare) {
        this.postnatalcare = postnatalcare;
    }

    public String getSicklast4wks() {
        return sicklast4wks;
    }

    public void setSicklast4wks(String sicklast4wks) {
        this.sicklast4wks = sicklast4wks;
    }

    public String getSortof() {
        return sortof;
    }

    public void setSortof(String sortof) {
        this.sortof = sortof;
    }

    public String getKindof() {
        return kindof;
    }

    public void setKindof(String kindof) {
        this.kindof = kindof;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public String getConsultedlt4wks() {
        return consultedlt4wks;
    }

    public void setConsultedlt4wks(String consultedlt4wks) {
        this.consultedlt4wks = consultedlt4wks;
    }

    public String getHouseowner() {
        return houseowner;
    }

    public void setHouseowner(String houseowner) {
        this.houseowner = houseowner;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLoni() {
        return loni;
    }

    public void setLoni(String loni) {
        this.loni = loni;
    }

    public String getCitizenimage() {
        return citizenimage;
    }

    public void setCitizenimage(String citizenimage) {
        this.citizenimage = citizenimage;
    }
}
