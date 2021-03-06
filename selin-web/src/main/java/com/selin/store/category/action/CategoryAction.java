package com.selin.store.category.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.roof.roof.dataaccess.api.Page;
import org.roof.roof.dataaccess.api.PageUtils;
import org.roof.spring.Result;
import org.roof.web.dictionary.entity.Dictionary;
import org.roof.web.dictionary.service.api.IDictionaryService;
import com.selin.store.category.entity.Category;
import com.selin.store.category.entity.CategoryVo;
import com.selin.store.category.service.api.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("selin/categoryAction")
public class CategoryAction {
	private ICategoryService categoryService;
	private IDictionaryService dictionaryService;

	// 加载页面的通用数据
	private void loadCommon(Model model){
		List<Dictionary> dicList =  dictionaryService.findByType("TEST");
		model.addAttribute("dicList", dicList);
	}

	@RequestMapping("/index")
	public String index() {
		return "/selin/category/category_index.jsp";
	}

	/*
	@RequestMapping("/list")
	public String list(Category category, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = categoryService.page(page, category);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		this.loadCommon(model);
		return "/selin/category/category_list.jsp";
	}
	*/

    @RequestMapping("/list")
    public @ResponseBody Result list(Category category, HttpServletRequest request, Model model) {
    Page page = PageUtils.createPage(request);
    page = categoryService.page(page, category);
    return new Result(Result.SUCCESS,page);
	}

	@RequestMapping("/tree")
	public @ResponseBody Result tree(Category category, HttpServletRequest request, Model model) {
        CategoryVo vo = categoryService.tree();
		return new Result(Result.SUCCESS, vo);
	}


	@RequestMapping("/create_page")
	public String create_page(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		this.loadCommon(model);
		return "/selin/category/category_create.jsp";
	}

	@RequestMapping("/update_page")
	public String update_page(Category category, Model model) {
		category = categoryService.load(category);
		model.addAttribute("category", category);
		this.loadCommon(model);
		return "/selin/category/category_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(Category category, Model model) {
		category = categoryService.load(category);
		model.addAttribute("category", category);
		this.loadCommon(model);
		return "/selin/category/category_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(Category category) {
		if (category != null) {
			categoryService.save(category);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/update")
	public @ResponseBody Result update(Category category) {
		if (category != null) {
			categoryService.updateIgnoreNull(category);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}

	@RequestMapping("/delete")
	public @ResponseBody Result delete(Category category) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		categoryService.delete(category);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setCategoryService(
			@Qualifier("categoryService") ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired(required = true)
	public void setDictionaryService(@Qualifier("dictionaryService") IDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}
}
