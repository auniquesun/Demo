# -*- coding: utf-8 -*-

import urllib.request

count = 0

with open('sites.txt') as f:
    for url in f:
        print("count:",count)
        site = urllib.request.urlopen(url)
        source = site.read()

        # print("type(source): ", type(source))
        
        filename = 'html_source/' + 'site' + str(count) + '_source.txt'
        
        file = open(filename,"wb")
        file.write(source)
        file.close()

        count += 1
