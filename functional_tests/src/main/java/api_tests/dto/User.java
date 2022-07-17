package api_tests.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

// maps to a user details in API response
public class User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User() {
    }

    public User(String name, String username, String email, Address address, String phone, String website,
                Company company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public User(String name, String username, String email, String street, String suite, String city, String zipcode,
                String lat, String lng, String phone, String website, String companyName, String catchPhrase, String bs) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = new Address(street, suite, city, zipcode, new Geo(lat, lng));
        this.phone = phone;
        this.website = website;
        this.company = new Company(companyName, catchPhrase, bs);
    }

    public User(List<String> data) {
        this.name = data.get(0);
        this.username = data.get(1);
        this.email = data.get(2);
        this.address = new Address(data.get(3), data.get(4), data.get(5), data.get(6), new Geo(data.get(7), data.get(8)));
        this.phone = data.get(9);
        this.website = data.get(10);
        this.company = new Company(data.get(11), data.get(12), data.get(13));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}