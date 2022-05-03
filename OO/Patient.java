package OO;

import java.sql.Date;

public class Patient {
int code;
String nom;
String prenom;
String adresse;
int tel;
String date;
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getAdresse() {
	return adresse;
}
public void setAdresse(String adresse) {
	this.adresse = adresse;
}
public int getTel() {
	return tel;
}
public void setTel(int tel) {
	this.tel = tel;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
 
@Override
public String toString() {
	return "Patient [code=" + code + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel
			+ ", date=" + date + "]";
}
public Patient(int code, String nom, String prenom, String adresse, int tel, String date) {
	super();
	this.code = code;
	this.nom = nom;
	this.prenom = prenom;
	this.adresse = adresse;
	this.tel = tel;
	this.date = date;
	 
}
public Patient() {
	super();
	// TODO Auto-generated constructor stub
}
 
}
