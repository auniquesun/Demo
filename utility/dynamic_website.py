# -*- coding: utf-8 -*_

"""
抓取 产品列表URL
"""

import re
import time

import scrapy
from selenium import webdriver

from shannon_spider.orm.product import ProductItem
from shannon_spider.shannon_base_spider import ShannonBaseSpider, ShannonCrawledItem

class ProductSpider(ShannonBaseSpider):
    """
    基类定义的属性，对它们初始化
    """
    name = 'product'
    result_orm_model = ProductItem

    # eBay 网站
    #allowed_domains = ['www.ebay.com']
    #start_urls = ['https://www.ebay.com']
    # gold678 网站
    allowed_domains = ['www.gold678.com']
    start_urls = ['http://www.gold678.com']
    # 基金网站
    #allowed_domains = ['fund.eastmoney.com']
    #start_urls = ['http://fund.eastmoney.com']

    def __init__(self, **kwargs):
        """
        初始化  时，先初始化它的基类
        """
        ShannonBaseSpider.__init__(self, **kwargs)
        self.driver = webdriver.PhantomJS() # driver是类变量，对其初始化
        
    def parse(self, response):
        """
        测试起始页面能否正常访问
        """
        if response.status != 200:
            self.logger.error('request: {} bad response status:{}'.format(response.request.url, response.status))
            return
        self.logger.info('请求的url为：')
        self.logger.info(response.request.url)
        self.logger.info('!!! [起始页面访问成功] !!! {}'.format(self.start_urls[0]))        
        
        # eBay 网站
        #url = 'http://www.ebay.com/sch/i.html?_odkw=books&_osacat=0&_trksid=p2045573.m570.l1313.TR0.TRC0.Xpython&_nkw=python&_sacat=0&_from=R40'
        # gold678 网站
        url = 'http://www.gold678.com/cnews'
        # 基金网站(需要动态加载的网站主页)
        #url = 'http://fund.eastmoney.com/fjjj.html#1_1__0__zdf,desc_1_yp'

        # 用driver访问url
        self.driver.get(url)
        
        try:
            #i = 0
            while True:
                try:
                # 定义翻页规则
                rule = '//a[@class="layui-laypage-next"]'

                # eBay 网站
                #next = self.driver.find_element_by_xpath('//a[@class="gspr next"]')
                # gold678 网站
                #next = self.driver.find_element_by_xpath('//a[@class="layui-laypage-next"]')
                # 基金网站
                #next = self.driver.find_element_by_xpath(rule.format(i+1))
                #i += 1

                next = self.driver.find_element_by_xpath(rule)
                next.click()
                time.sleep(5)   # 程序睡眠5秒，给网页数据加载留出时间
                
                self.logger.info('='*15)
                self.logger.info("!!! [翻页成功] !!")
                self.logger.info('='*15)
                    
        except Exception as err:
            self.logger.warning('{}'.format(err))
            self.logger.warning("!!! [翻页出错] !!! {}".format(response.request.url))
            break

        finally:
            self.driver.close() # 最后要关闭driver
