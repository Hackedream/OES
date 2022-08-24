package com.wakuwaku.oes3.controller;


import com.wakuwaku.oes3.service.IChecklistService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ania
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/checklist")
@CrossOrigin
public class ChecklistController {

    @Resource
    IChecklistService checklistService;


}
