package domain.category;

public class CategoryVo {
 
	public int cateNo;
	public String cateName;
	public int horseNo;
	public String horse;
	public CategoryVo() {
		super();
	}
	public CategoryVo(int cateNo, String cateName, int horseNo, String horse) {
		super();
		this.cateNo = cateNo;
		this.cateName = cateName;
		this.horseNo = horseNo;
		this.horse = horse;
		
		
	}
	public CategoryVo(String horse) {
		super();
		this.horse = horse;
	}
	public int getCateNo() {
		return cateNo;
	}
	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	public int getHorseNo() {
		return horseNo;
	}
	public void setHorseNo(int horseNo) {
		this.horseNo = horseNo;
	}
	public String getHorse() {
		return horse;
	}
	public void setHorse(String horse) {
		this.horse = horse;
	}
	
	
	
	
}
	
