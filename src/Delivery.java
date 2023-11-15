import java.util.Objects;

public class Delivery {
	protected Members member;
	protected String fromCity;
	protected String targetCity;
	protected Integer code;
	public Delivery(Members member, String fromCity, String targetCity, Integer code) {
		super();
		this.member = member;
		this.fromCity = fromCity;
		this.targetCity = targetCity;
		this.code = code;
	}
	public Delivery(Integer code)
	{
		this.code=code;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(code, other.code);
	}
	public Members getMember() {
		return member;
	}
	public void setMember(Members member) {
		this.member = member;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getTargetCity() {
		return targetCity;
	}
	public void setTargetCity(String targetCity) {
		this.targetCity = targetCity;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "member(id)=" + member.getId() + ", fromCity=" + fromCity + ", targetCity=" + targetCity + ", code=" + code;
	}
	public String toString2() {
		return "[member(id)=" + member.getId() + ", fromCity=" + fromCity + ", targetCity=" + targetCity + ", code=" + code;
	}


}