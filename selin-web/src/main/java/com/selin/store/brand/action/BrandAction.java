package com.selin.store.brand.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import com.selin.store.brand.entity.Brand;
import com.selin.store.brand.entity.BrandVo;
import com.selin.store.brand.service.api.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("selin/brandAction")
public class BrandAction {
	private IBrandService brandService;
	private IDictionaryService dictionaryService;

	// 加载页面的通用数据
	private void loadCommon(Model model){
		List<Dictionary> dicList =  dictionaryService.findByType("TEST");
		model.addAttribute("dicList", dicList);
	}

	@RequestMapping("/index")
	public String index() {
		return "/selin/brand/brand_index.jsp";
	}

	/*
	@RequestMapping("/list")
	public String list(Brand brand, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = brandService.page(page, brand);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		this.loadCommon(model);
		return "/selin/brand/brand_list.jsp";
	}
	*/

    @RequestMapping("/list")
    public @ResponseBody Result list(Brand brand, HttpServletRequest request, Model model) {
    Page page = PageUtils.createPage(request);
    page = brandService.page(page, brand);
    return new Result(Result.SUCCESS,page);
	}
	
	
	@RequestMapping("/create_page")
	public String create_page(Model model) {
		Brand brand = new Brand();
		model.addAttribute("brand", brand);
		this.loadCommon(model);
		return "/selin/brand/brand_create.jsp";
	}
	
	@RequestMapping("/update_page")
	public String update_page(Brand brand, Model model) {
		brand = brandService.load(brand);
		model.addAttribute("brand", brand);
		this.loadCommon(model);
		return "/selin/brand/brand_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(Brand brand, Model model) {
		brand = brandService.load(brand);
		model.addAttribute("brand", brand);
		this.loadCommon(model);
		return "/selin/brand/brand_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(Brand brand) {
		if (brand != null) {
			brandService.save(brand);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(Brand brand) {
		if (brand != null) {
			brandService.updateIgnoreNull(brand);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(Brand brand) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		brandService.delete(brand);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setBrandService(
			@Qualifier("brandService") IBrandService brandService) {
		this.brandService = brandService;
	}

	@Autowired(required = true)
	public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
