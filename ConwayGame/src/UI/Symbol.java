package UI;

public enum Symbol {

    A("\033[37m"+"#"+"\033[0m"),
    B("\033[31m"+"#"+"\033[0m"),
    C("\033[32m"+"#"+"\033[0m"),
    D("\033[33m"+"#"+"\033[0m"),
    E("\033[34m"+"#"+"\033[0m"),
    F("\033[35m"+"#"+"\033[0m"),
    G("\033[36m"+"#"+"\033[0m");

    private final String value;
    Symbol(String value){
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
