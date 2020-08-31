use stockexchange;
DROP TABLE IF EXISTS `companystockprice`;
CREATE TABLE `companystockprice` (
  `companyStockPriceId` int(11) NOT NULL AUTO_INCREMENT,
  `companyCode` decimal(10,0) DEFAULT NULL,
  `stockExchange` varchar(20) DEFAULT NULL,
  `currentPrice` double DEFAULT NULL,
  `stockPriceDate` date DEFAULT NULL,
  `stockPriceTime` time DEFAULT NULL,
  PRIMARY KEY (`companyStockPriceId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

select*from companystockprice;