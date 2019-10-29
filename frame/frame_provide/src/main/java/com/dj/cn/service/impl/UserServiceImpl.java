package com.dj.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.cn.domain.entry.User;
import com.dj.cn.excleUtil.excel.ExcelConfig;
import com.dj.cn.excleUtil.excel.enums.ExcelTemplate;
import com.dj.cn.mapper.UserMapper;
import com.dj.cn.service.UserService;
import devutility.external.poi.PoiUtils;
import devutility.external.poi.common.ExcelType;
import devutility.external.poi.models.FieldColumnMap;
import devutility.external.poi.utils.WorkbookUtils;
import devutility.internal.text.format.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @version V1.0
 * @Title: UserServiceImpl
 * @Package: com.dj.cn.service.impl
 * @author: Lenovo
 * @date: 2019/7/19 16:48
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> findUserById(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_type",id);
        return this.list(queryWrapper);
    }

    @Override
    public void exportExcleUser(HttpServletRequest request, HttpServletResponse responses, int i) {
        InputStream templateInputStream = null;
        try {
            //xls和xlsx格式时，HSSFWorkbook针对xls，XSSFWorkbook针对xlsx
            //String templatePath = "/" + ExcelConfig.EXCEL_TEMPLATE_PATH + "/" + ExcelTemplate.USER_ID.getDescription();
            String templatePath = "/template/xlsx/User.xlsx";
            templateInputStream = this.getClass().getResourceAsStream(templatePath);
            String fileName = java.net.URLEncoder.encode(("user" + "_"
                    + DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".xlsx"), "UTF-8");
            responses.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            responses.setContentType("application/vnd.ms-excel;charset=UTF-8");
            FieldColumnMap<User> fieldColumnMap = User.getFieldColumnMap();
            List<User> userList = this.findUserById(String.valueOf(i));
            PoiUtils.save(templateInputStream, "Sheet1", fieldColumnMap, userList, responses.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (templateInputStream != null) {
                try {
                    templateInputStream.close();
                } catch (IOException io) {
                }
            }
        }
    }

}
