package xb.mall.bean;


public class ProductDetail {
    private String productId;

    private String detailDesc;

    private String specificationTable;

    public ProductDetail(){}

    public ProductDetail(String productId){
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public String getSpecificationTable() {
        return specificationTable;
    }

    public void setSpecificationTable(String specificationTable) {
        this.specificationTable = specificationTable == null ? null : specificationTable.trim();
    }
}