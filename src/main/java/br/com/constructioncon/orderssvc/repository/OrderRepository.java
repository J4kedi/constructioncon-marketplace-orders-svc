
package br.com.constructioncon.orderssvc.repository;

import br.com.constructioncon.orderssvc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
