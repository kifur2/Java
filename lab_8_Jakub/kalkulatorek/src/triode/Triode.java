package triode;

public class Triode{
    private Boolean isANumber;
    private Boolean hasADot;
    private Integer index;

    public Triode(Boolean pIsANumber, Boolean pHasADot, Integer pIndex){
        this.isANumber = pIsANumber;
        this.hasADot = pHasADot;
        this.index = pIndex;
    }
    public Boolean getIsANumber(){
        return this.isANumber;
    }
    public Boolean getHasADot(){
        return this.hasADot;
    }
    public Integer getIndex(){
        return this.index;
    }
    public void setIsANumber(Boolean newIsANumber){
        this.isANumber = newIsANumber;
    }
    public void setHasADot(Boolean newHasADot){
        this.hasADot = newHasADot;
    }
    public void setIndex(Integer newInteger){
        this.index = newInteger;
    }

    public String toString(){
        return "(" + getIsANumber() + ", " + getHasADot() + ", " + getIndex() + ") ";
    }

}
