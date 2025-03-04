package com.example.element;

import org.openqa.selenium.By;

public class productElement {
    public By productBasicInfoSection = By.xpath("//*[normalize-space()='Thông tin cơ bản']");
    public By productOriginInfoSection = By.xpath("//*[normalize-space()='Thông tin xuất xứ']");
    public By itemCode = By.xpath("//label[contains(normalize-space(),'Item code')]/following::input[1]");
    public By description = By.xpath("//label[normalize-space()='Mô tả']/following::input[1]");
    public By unitSl1 = By.xpath("//label[normalize-space()='Đơn vị tính (SL1)']/following::p-dropdown[1]");
    public By unitSl2 = By.xpath("//label[normalize-space()='Đơn vị tính (SL2)']/following::p-dropdown[1]");
    public By conversionRateSl1ToSl2 = By.xpath("//label[normalize-space()='Tỉ lệ quy đổi SL1 với SL2']/following::input[1]");
    public By declarationQuantityUnit = By.xpath("//label[normalize-space()='ĐVT số lượng 2 trên tk']/following::p-dropdown[1]");
    public By exportTax = By.xpath("//label[normalize-space()='Thuế XK']/following::input[1]");
    public By standardProductSwitch = By.xpath("//*[normalize-space()='Hàng tiêu chuẩn']/preceding::p-inputswitch[1]");
    public By quantityPerPackage = By.xpath("//label[normalize-space()=\"Q'ty/pkg\"]/following::input[1]");
    public By unit = By.xpath("//label[normalize-space()='Unit']/following::p-dropdown[1]");
    public By grossWeight = By.xpath("//label[normalize-space()='G.W (kg/pkg)']/following::input[1]");
    public By netWeightKgPcPkg = By.xpath("//label[normalize-space()='N.W (kg/pc/pkg)']/following::input[1]");
    public By dimensionLength = By.xpath("//label[normalize-space()='Dimention (mm/pkg)']/following::input[1]");
    public By dimensionWidth = By.xpath("//label[normalize-space()='Dimention (mm/pkg)']/following::input[2]");
    public By dimensionHeight = By.xpath("//label[normalize-space()='Dimention (mm/pkg)']/following::input[3]");
    public By dimensionM3 = By.xpath("//label[normalize-space()='Dimention (m3)']/following::input[1]");
    public By netWeightPerSet = By.xpath("//label[normalize-space()='NW/pc/set']/following::input[1]");
    public By unitHq = By.xpath("//label[normalize-space()='Unit/ HQ']/following::p-dropdown[1]");

    public By originHq = By.xpath("//label[normalize-space()='Xuất xứ/ HQ']/following::input[1]");
    public By originDeclarationProductName = By.xpath("//label[normalize-space()='Xuất xứ/TK (tên hàng)']/following::input[1]");
    public By originLabel = By.xpath("//label[normalize-space()='Xuất xứ trên tem nhãn']/following::input[1]");
    public By tusOrigin = By.xpath("//label[normalize-space()=\"TUS'origin\"]/following::input[1]");
    public By originCriteria = By.xpath("//label[normalize-space()='Tiêu chí xuất xứ']/following::p-dropdown[1]");
    public By formCo = By.xpath("//label[normalize-space()='Form C/O']/following::p-dropdown[1]");
    public By hsCodeApplyTime = By.xpath("//label[normalize-space()='Thời điểm áp HS code']/following::input[1]");
    public By productType = By.xpath("//label[normalize-space()='Loại hàng']/following::p-dropdown[1]");
    public By originCalculationLink = By.xpath("//label[normalize-space()='Link tính xuất xứ']/following::input[1]");
    public By originApprovalDate = By.xpath("//label[normalize-space()='Ngày phê duyệt xuất xứ']/following::input[1]");
    public By originPic = By.xpath("//label[normalize-space()='PIC làm xuất xứ']/following::p-dropdown[1]");
    public By originMail = By.xpath("//label[normalize-space()='Mail gửi xuất xứ']/following::input[1]");
    public By originRequestDepartment = By.xpath("//label[normalize-space()='Bộ phận yêu cầu xuất xứ']/following::p-dropdown[1]");
    public By originConfirmationTime = By.xpath("//label[normalize-space()='Thời điểm xác nhận xuất xứ']/following::input[1]");
    public By originNote = By.xpath("//label[normalize-space()='Ghi chú']/following::textarea[1]");
}
