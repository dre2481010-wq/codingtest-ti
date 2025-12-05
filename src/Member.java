public class Member {

    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private int registrationYear;
    private String membershipType;

    static int totalMembers = 0;
    public Member() {
        totalMembers++;
        this.memberId = String.format("MBR%03d", totalMembers);

        // nilai default
        this.name = "";
        this.email = "";
        this.phoneNumber = "";
        this.registrationYear = 2025;
        this.membershipType = "Silver";
    }
    public Member(String name, String email, String phoneNumber,
                  int registrationYear, String membershipType) {

        totalMembers++;
        this.memberId = String.format("MBR%03d", totalMembers);

        // langsung set tanpa validasi berat
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.registrationYear = registrationYear;
        this.membershipType = membershipType;
    }
    public void displayInfo() {
        System.out.println("============================================");
        System.out.println("[" + memberId + "] " + name);
        System.out.println("Email         : " + email);
        System.out.println("Phone         : " + phoneNumber);
        System.out.println("Membership    : " + membershipType + " " + getMembershipStars());
        System.out.println("Tahun Daftar  : " + registrationYear);
        System.out.println("Durasi Member : " + getMembershipDuration() + " tahun");
        System.out.println("Batas Pinjam  : " + getMaxBorrowLimit() + " buku");
        System.out.println("Diskon Denda  : " + (int)(getMembershipDiscount() * 100) + "%");
        System.out.println("--------------------------------------------");
    }
    public void upgradeMembership(String newType) {

        if (!isValidType(newType)) {
            System.out.println("✗ Error: Tipe membership tidak valid!");
            return;
        }

        int oldLevel = getLevel(this.membershipType);
        int newLevel = getLevel(newType);

        if (newLevel > oldLevel) {
            this.membershipType = newType;
            System.out.println("✓ " + name + " berhasil di-upgrade ke " + newType + "!");
        } else if (newLevel == oldLevel) {
            System.out.println("✗ Error: Sudah berada pada level " + newType);
        } else {
            System.out.println("✗ Error: Upgrade tidak valid! (Silver → Gold → Platinum)");
        }
    }
    private int getLevel(String type) {
        switch (type) {
            case "Silver": return 1;
            case "Gold": return 2;
            case "Platinum": return 3;
            default: return 0;
        }
    }

    private boolean isValidType(String type) {
        return type.equals("Silver") || type.equals("Gold") || type.equals("Platinum");
    }
    public int getMaxBorrowLimit() {
        switch (this.membershipType) {
            case "Platinum": return 10;
            case "Gold": return 7;
            default: return 5; // Silver
        }
    }

    public int getMembershipDuration() {
        return 2025 - this.registrationYear;
    }

    public double getMembershipDiscount() {
        switch (this.membershipType) {
            case "Platinum": return 0.50;
            case "Gold": return 0.30;
            default: return 0.10;
        }
    }

    private String getMembershipStars() {
        switch (this.membershipType) {
            case "Platinum": return "⭐⭐⭐";
            case "Gold": return "⭐⭐";
            default: return "⭐";
        }
    }

    public static int getTotalMembers() {
        return totalMembers;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("✗ Error: Nama tidak boleh kosong");
        }
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("✗ Error: Email tidak valid");
        }
        this.email = email;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 10 || phoneNumber.length() > 13) {
            throw new IllegalArgumentException("✗ Error: Nomor telepon harus 10-13 digit");
        }
        this.phoneNumber = phoneNumber;
    }

    public int getRegistrationYear() { return registrationYear; }

    public void setRegistrationYear(int registrationYear) {
        if (registrationYear < 2015 || registrationYear > 2025) {
            throw new IllegalArgumentException("✗ Error: Tahun registrasi harus 2015-2025");
        }
        this.registrationYear = registrationYear;
    }

    public String getMembershipType() { return membershipType; }

    public void setMembershipType(String membershipType) {
        if (!isValidType(membershipType)) {
            throw new IllegalArgumentException("✗ Error: Membership harus Silver/Gold/Platinum");
        }
        this.membershipType = membershipType;
    }
}
