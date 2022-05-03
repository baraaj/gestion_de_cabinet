package OO;

public class Ordonnance {
	int codeO,codeC;
	String ord;

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public int getCodeO() {
		return codeO;
	}

	public void setCodeO(int codeO) {
		this.codeO = codeO;
	}

	public int getCodeC() {
		return codeC;
	}

	public void setCodeC(int codeC) {
		this.codeC = codeC;
	}

	@Override
	public String toString() {
		return "Ordonnance [codeO=" + codeO + ", codeC=" + codeC + ", ord=" + ord + "]";
	}

	public Ordonnance(int codeO, int codeC, String ord) {
		super();
		this.codeO = codeO;
		this.codeC = codeC;
		this.ord = ord;
	}

	 
	

}
