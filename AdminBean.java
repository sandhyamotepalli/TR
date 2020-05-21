package beans;

import java.sql.Blob;
import java.sql.Timestamp;

public class AdminBean {

	//login details
	private String username=null;
	private String password =null;
	private String usertype=null;
	private Timestamp creationdatetime = null;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Timestamp getCreationdatetime() {
		return creationdatetime;
	}
	public void setCreationdatetime(Timestamp creationdatetime) {
		this.creationdatetime = creationdatetime;
	}
	
	
	
	//training details
	private int trainingid=0;
	private String coursename=null;
	private int duration=0;
	private int fee=0;
	private String trainername=null; 
	private String trainerinfo=null; 
	private String startdate=null;
	private int feeindollar=0;
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}



	private String timings=null; 
	private String mode=null;

	
	public int getTrainingid() {
		return trainingid;
	}
	public void setTrainingid(int trainingid) {
		this.trainingid = trainingid;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getTrainername() {
		return trainername;
	}
	public void setTrainername(String trainername) {
		this.trainername = trainername;
	}
	public String getTrainerinfo() {
		return trainerinfo;
	}
	public void setTrainerinfo(String trainerinfo) {
		this.trainerinfo = trainerinfo;
	}

	
	public int getFeeindollar() {
		return feeindollar;
	}
	public void setFeeindollar(int feeindollar) {
		this.feeindollar = feeindollar;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}


	//careers
	private String fullname=null;
	private String emailid=null;
	private String contactNumber =null;
	private String locationpreference=null;
	private int itexperience=0;
	private int relevantexperience =0;
	private int jobcode=0;
	private Blob resume =null;
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getLocationpreference() {
		return locationpreference;
	}
	public void setLocationpreference(String locationpreference) {
		this.locationpreference = locationpreference;
	}
	public int getItexperience() {
		return itexperience;
	}
	public void setItexperience(int itexperience) {
		this.itexperience = itexperience;
	}
	public int getRelevantexperience() {
		return relevantexperience;
	}
	public void setRelevantexperience(int relevantexperience) {
		this.relevantexperience = relevantexperience;
	}
	public int getJobcode() {
		return jobcode;
	}
	public void setJobcode(int jobcode) {
		this.jobcode = jobcode;
	}
	public Blob getResume() {
		return resume;
	}
	public void setResume(Blob resume) {
		this.resume = resume;
	}
	
	private String description1=null;
	private String description2=null;
	private String description3=null;
	private String position=null;
	private String experience =null;

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
    public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getDescription3() {
		return description3;
	}
	public void setDescription3(String description3) {
		this.description3 = description3;
	}



	private int careersid=0;

	public int getCareersid() {
		return careersid;
	}
	public void setCareersid(int careersid) {
		this.careersid = careersid;
	}
	
	private int trainerid=0;
	private String traineremail=null;
	private String technology=null;

	public int getTrainerid() {
		return trainerid;
	}
	public void setTrainerid(int trainerid) {
		this.trainerid = trainerid;
	}
	public String getTraineremail() {
		return traineremail;
	}
	public void setTraineremail(String traineremail) {
		this.traineremail = traineremail;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
	private String trainerstatus=null;

	public String getTrainerstatus() {
		return trainerstatus;
	}
	public void setTrainerstatus(String trainerstatus) {
		this.trainerstatus = trainerstatus;
	}
	
	/*trainer videos*/
	private int videoid=0;
	private String videoheading=null;
	private String uniquestorageid=null;
	
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public String getVideoheading() {
		return videoheading;
	}
	public void setVideoheading(String videoheading) {
		this.videoheading = videoheading;
	}
	public String getUniquestorageid() {
		return uniquestorageid;
	}
	public void setUniquestorageid(String uniquestorageid) {
		this.uniquestorageid = uniquestorageid;
	}



	/*Registered User*/
	private int userid=0;
	private String registeredname =null;
	private String usertechnology =null;
	private String useremail=null;
	private String userstatus=null;
	private int loginexpiry=0;
	private String expirydate=null;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getRegisteredname() {
		return registeredname;
	}
	public void setRegisteredname(String registeredname) {
		this.registeredname = registeredname;
	}
	public String getUsertechnology() {
		return usertechnology;
	}
	public void setUsertechnology(String usertechnology) {
		this.usertechnology = usertechnology;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	public int getLoginexpiry() {
		return loginexpiry;
	}
	public void setLoginexpiry(int loginexpiry) {
		this.loginexpiry = loginexpiry;
	}
	public String getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	
	private String bucketname=null;
	private String generationid=null;

	public String getGenerationid() {
		return generationid;
	}
	public void setGenerationid(String generationid) {
		this.generationid = generationid;
	}
	public String getBucketname() {
		return bucketname;
	}
	public void setBucketname(String bucketname) {
		this.bucketname = bucketname;
	}
	 
	private String link = null;

	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	private String batchnumber=null;

	public String getBatchnumber() {
		return batchnumber;
	}
	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}
	
	//training content
	private int contentid=0;
	private String trainingcontent=null;

	public int getContentid() {
		return contentid;
	}
	public void setContentid(int contentid) {
		this.contentid = contentid;
	}
	public String getTrainingcontent() {
		return trainingcontent;
	}
	public void setTrainingcontent(String trainingcontent) {
		this.trainingcontent = trainingcontent;
	}
   
	private String loginstatus = null;

	public String getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}

	private String pagename=null;

	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	
}

