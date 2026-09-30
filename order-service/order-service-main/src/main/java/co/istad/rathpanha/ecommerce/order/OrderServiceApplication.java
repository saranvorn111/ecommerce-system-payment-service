package co.istad.rathpanha.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
    "co.istad.rathpanha.ecommerce.order.persistence"
})
@EnableJpaRepositories(basePackages =  {
    "co.istad.rathpanha.ecommerce.order.persistence"
})
@SpringBootApplication
public class OrderServiceApplication {
   public static void main(String[] args) {
      SpringApplication.run(OrderServiceApplication.class, args);
   }
}
