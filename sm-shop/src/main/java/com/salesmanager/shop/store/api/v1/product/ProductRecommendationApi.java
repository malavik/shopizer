package com.salesmanager.shop.store.api.v1.product;

import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.catalog.product.PricingService;


import javax.persistence.Query;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.image.ProductImageService;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.model.catalog.product.PersistableImage;
import com.salesmanager.shop.populator.catalog.PersistableProductImagePopulator;
import com.salesmanager.shop.store.api.exception.ResourceNotFoundException;
import com.salesmanager.shop.store.api.exception.ServiceRuntimeException;
import com.salesmanager.shop.store.api.exception.UnauthorizedException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

/**
 * API to get product recommendations
 *
 * @author Malavika Murali
 */
@Controller
public class ProductRecommendationApi  {
    
 // @Inject private ProductFacade productFacade;

 // @Inject private StoreFacade storeFacade;

 // @Inject private LanguageUtils languageUtils;

  @Inject private ProductService productService;
  @Inject private LanguageService languageService;
  @Inject private PricingService pricingService;


 // @Inject private ProductReviewService productReviewService;

    
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductRecommendationApi.class);

  @PersistenceContext
    private EntityManager em;

    // /api/v1/products/recommended
    @RequestMapping(value = "/api/v1/products/recommended", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public /*List<Product>*/ ResponseEntity<?> /*String*/ ProductRecommendation(@RequestParam("category") Long category, @RequestParam("lang") String lang) throws Exception      
        {
        /* TODO: ADD CODE TO ACCEPT PARAMETERS HERE.*/
        Map<String,Language> langs = languageService.getLanguagesMap();
        Language language = langs.get(lang);



        /* TODO: ADD CODE TO QUERY THE DATABASE HERE.*/
        //List<Long> catList = new ArrayList<Long>();
        //catList.add(category);
        List<Product> products = productService.getProductRecommendation(category,language);

        /*
		StringBuilder qs = new StringBuilder();
		qs.append("select distinct p from Product as p ");
		qs.append("join fetch p.merchantStore merch ");
		qs.append("join fetch p.availabilities pa ");
		qs.append("left join fetch pa.prices pap ");
		
		qs.append("join fetch p.descriptions pd ");
		qs.append("join fetch p.categories categs ");
		
		qs.append("left join fetch pap.descriptions papd ");
		
		//images
		qs.append("left join fetch p.images images ");
		
		//options (do not need attributes for listings)
		qs.append("left join fetch p.attributes pattr ");
		qs.append("left join fetch pattr.productOption po ");
		qs.append("left join fetch po.descriptions pod ");
		qs.append("left join fetch pattr.productOptionValue pov ");
		qs.append("left join fetch pov.descriptions povd ");
		
		//other lefts
		qs.append("left join fetch p.manufacturer manuf ");
		qs.append("left join fetch manuf.descriptions manufd ");
		qs.append("left join fetch p.type type ");
		qs.append("left join fetch p.taxClass tx ");
		
		//RENTAL
		qs.append("left join fetch p.owner owner ");
		qs.append("where categs.id in (:cid) ");
		qs.append("and pd.language.code=:lang and papd.language.code=:lang ");
		qs.append("and p.available=true and p.dateAvailable<=:dt ");

        String hql = qs.toString();
		Query q = this.em.createQuery(hql);

    	q.setParameter("cid", category);
    	q.setParameter("lang", lang);
    	q.setParameter("dt", new Date());
*/
    	
//    	@SuppressWarnings("unchecked")
		//List<Product> products =  q.getResultList();
        //return products;
        //String response = "called /api/v1/products/recommended with getProducts()" + Long.toString(category) + " " + lang;
        //return response;
        
        //List result = q.getResultList();
        //return result.toString();
        
        return ResponseEntity.status(HttpStatus.OK).body(products);
        

        }
}