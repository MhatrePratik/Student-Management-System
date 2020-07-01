class Student {

   	public int rno;
    public String name;
    public int m1;
    public int m2;
    public int m3;

	public Student() {
		rno=0;
		name="";
		m1=0;
		m2=0;
		m3=0;
	} 

	public Student(int rno,String name,int m1, int m2, int m3 ) {
		this.rno=rno;
		this.name=name;
		this.m1=m1;
		this.m2=m2;
		this.m3=m3;
		}

	public int getRno() {					//Acessor
		return rno;
		}

	public void setRno(int rno) {				//Mutator
		 this.rno = rno;
		}

	public String getName() {				//Acessor
		return name;
		}
	public void setName(String name) {			//Mutator
		this.name = name;
		}
	

	public int getm1() {				//Acessor
		return m1;
		}

	public void setm1(int m1) {				//Mutator
		 this.m1 = m1;
		}

	public int getm2() {				//Acessor
			return m2;
		}

	public void setm2(int m2) {				//Mutator
		 this.m2 = m2;
		}

	public int getm3() {				//Acessor
		return m3;
		}

	public void setm3(int m3) {			//Mutator
		 this.m3 = m3;
		}		
}