package days.day4;

import java.util.Set;

public class Passport implements PassportCandidate{
    private final String byr, iyr, eyr, hgt, hcl, ecl, pid;

    public Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid) {
        this.byr = byr;
        this.iyr = iyr;
        this.eyr = eyr;
        this.hgt = hgt;
        this.hcl = hcl;
        this.ecl = ecl;
        this.pid = pid;
    }

    @Override
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
        return isYearBetweenInclusive(byr,1920, 2002);
    }

    private boolean isIssueYearValid(){
        return isYearBetweenInclusive(iyr,2010, 2020);
    }

    private boolean isExpirationYearValid(){
        return isYearBetweenInclusive(eyr,2020, 2030);
    }

    private boolean isYearBetweenInclusive(String year, int begin, int end){
        if(year.length() != 4) return false;
        int val = Integer.parseInt(year);
        return val >= begin && val <= end;
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
