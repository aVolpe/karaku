package py.una.med.base.dao.where;

import org.hibernate.criterion.Criterion;

public interface IClause {

	public Criterion getCriterion();

}