package OO;

public class Consultation {
	int codec;
	String typeC;
	String remarques;
	int codep;
	String dateC;
	public int getCodec() {
		return codec;
	}
	public void setCodec(int codeC) {
		this.codec = codeC;
	}
	public String getTypeC() {
		return typeC;
	}
	public void setTypeC(String typeC) {
		this.typeC = typeC;
	}
	public String getRemarques() {
		return remarques;
	}
	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}
	public int getCodep() {
		return codep;
	}
	public void setCodep(int codeP) {
		this.codep = codeP;
	}
	public String getDateC() {
		return dateC;
	}
	public void setDateC(String dateC) {
		this.dateC = dateC;
	}
	public Consultation(int codeC, String typeC, String remarques, int codeP, String dateC) {
		super();
		this.codec = codeC;
		this.typeC = typeC;
		this.remarques = remarques;
		this.codep = codeP;
		this.dateC = dateC;
	}
	@Override
	public String toString() {
		return "Consultation [codeC=" + codec + ", typeC=" + typeC + ", remarques=" + remarques + ", codeP=" + codep
				+ ", dateC=" + dateC + "]";
	}
	

}
