# EpiScanGIS

Meningococcal Disease Surveillance in Germany

## What is EpiScanGIS?

Detecting meningococcal disease outbreaks as early as possible is important in order to minimize the number of infections amongst the population. EpiScanGIS uses a Geographical Information System (GIS) to present maps showing the distribution of all cases of meningococcal disease, that have been registered at the [National Reference Centre for Meningococci (NRZM)](https://www.hygiene.uni-wuerzburg.de/meningococcus/startseite/) in Germany. Thus it acts as an information system, that provides you with easy to access and timely information on meningococcal infections in Germany.

EpiScanGIS has been developed 2006 at the NRZM in cooperation with the [Chair of Computer Science II](https://se.informatik.uni-wuerzburg.de/home/). It initiated from the diploma thesis of Markus Reinhardt held at the Chair of Computer Science II, University of Würzburg.

## Monitoring Invasive Meningococcal Disease in Germany

One goal of this interdisciplinary project is, to provide a publicly accessible system for computer-aided epidemiological surveillance of meningococcal disease in Germany.

EpiScanGIS provides you with weekly updated, dynamically generated maps. The underlying database is a result of the consistent work at the NRZM, which, as a national reference laboratory, types the majority of all meningococcal infections in Germany using microbiological methods.

We used several non-commercial software components in this project, that each apart accomplish bits in the overall process:

- The [PostgreSQL](https://www.postgresql.org/) object-relational database stores anonymized epidemiological information.
- [PostGIS](http://www.postgis.org/) adds support for geographic objects an queries to PostgreSQL.
- [UMN Mapserver](https://mapserver.gis.umn.edu/) is responsible for generating maps using the data provided by the database.
- [OpenLaszlo](https://www.openlaszlo.org/) was used to generate a Flash-based Rich Internet Application, that enable intuitive access to epidemiological data. After discontinuation of flash in 2021 it has been replaced by a single page application. 

## Early Outbreak Detection

The second component of this online system provides the health community with an early warning cluster detection system.

The resulting reports and maps are not publicly available, but they help registered and authorized public health authorities in spotting possible risk areas and taking preventive measures.

EpiScanGIS uses the Software [SaTScan™](https://www.satscan.org/) for the 'cluster detection' process. The underlying dataset is automatically scanned on a weekly basis to detect potential diseases outbreaks in a timely manner.

SaTScan™ is a trademark of Martin Kulldorff. The SaTScan™ software was developed under the joint auspices of Martin Kulldorff, of the National Cancer Institute and of Farzad Mostashari at the New York City Department of Health and Mental Hygiene.

## Publications

EpiScanGIS and its implementation has been published in the International Journal of Health Geographics. Please download the paper under the following link: http://www.ij-healthgeographics.com/content/7/1/33

>      Reinhardt M, Elias J, Albert J, Frosch M, Harmsen D, Vogel U.
>      EpiScanGIS: an online geographic surveillance system for meningococcal disease.
>      Int J Health Geogr.
>      2008 Jul 1;7:33.
>      PubMed PMID: 18593474; PubMed Central PMCID: PMC2483700
      
SaTScan analysis based on meningococcal finetypes has been published by Elias et al. Please download the paper under the following link: http://www.cdc.gov/ncidod/EID/vol12no11/06-0682.htm

>      Elias J, Harmsen D, Claus H, Hellenbrand W, Frosch M, Vogel U.
>      Spatiotemporal analysis of invasive meningococcal disease, Germany.
>      Emerg Infect Dis.
>      2006 Nov;12(11):1689-95.
>      PubMed PMID: 17283618.
      
