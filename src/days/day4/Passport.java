package days.day4;

import java.util.Set;

public class Passport implements PassportCandidate{
    private String byr, iyr, eyr, hgt, hcl, ecl, pid;

    public Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid) {
        this.byr = byr;
        this.iyr = iyr;
        this.eyr = eyr;
        this.hgt = hgt;
        this.hcl = hcl;
        this.ecl = ecl;
        this.pid = pid;
    }

    public boolean isValidPassport(){
        return isBirthYearValid() &&
                isIssueYearValid() &&
                isExpirationYearValid() &&
                isHeightValid() &&
                isHairColourValid() &&
                isEyeColourValid() &&
                isPassportIDValid();
    }

    private boolean isBirthYearValid(){
        if(byr.length() != 4) return false;
        int val = Integer.parseInt(byr);
        return val >= 1920 && val <= 2002;
    }

    private boolean isIssueYearValid(){
        if(iyr.length() != 4) return false;
        int val = Integer.parseInt(iyr);
        return val >= 2010 && val <= 2020;
    }

    private boolean isExpirationYearValid(){
        if(eyr.length() != 4) return false;
        int val = Integer.parseInt(eyr);
        return val >= 2020 && val <= 2030;
    }

    private boolean isHeightValid(){
        if(hgt.endsWith("cm")){
            int val = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            return val >= 150 && val <= 193;
        }
        else if(hgt.endsWith("in")){
            int val = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            return val >= 59 && val <= 76;
        }
        else
            return false;
    }

    private boolean isHairColourValid(){
        return hcl.matches("#[0-9a-f]{6}");
    }

    private boolean isEyeColourValid(){
        Set<String> validColours = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return validColours.contains(ecl);
    }

    private boolean isPassportIDValid(){
        return pid.matches("[0-9]{9}");
    }
}
