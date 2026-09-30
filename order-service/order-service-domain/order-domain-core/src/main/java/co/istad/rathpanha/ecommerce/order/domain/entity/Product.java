package co.istad.rathpanha.ecommerce.order.domain.entity;

import co.istad.rathpanha.ecommerce.domain.entity.BaseEntity;
import co.istad.rathpanha.ecommerce.domain.valueobject.Money;
import co.istad.rathpanha.ecommerce.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money price;

  private Product(Builder builder) {
    super.setId(builder.id);
    name = builder.name;
    price = builder.price;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getName() {
    return name;
  }

  public Money getPrice() {
    return price;
  }

  public void updateConfirmedNameAndPrice(String name, Money price) {
    this.name = name;
    this.price = price;
  }


  public static final class Builder {
    private ProductId id;
    private String name;
    private Money price;

    private Builder() {
    }

    public Builder id(ProductId val) {
      id = val;
      return this;
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder price(Money val) {
      price = val;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }
}
