CREATE OR REPLACE FUNCTION episcangis_r_kernel3d()
  RETURNS text AS
$BODY$
    library(splancs)
    conn <- dbConnect(PgSQL(), host = "localhost", dbname = "epidegis_works", user="mreinhardt", password="Iban12P")
    result <- pg.spi.exec ("SELECT X(the_geom)::float4 AS x FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes WHERE attributes.name='Serogroup' AND value='B'")
    res <- dbSendQuery(conn, "select X(the_geom) AS x, Y(the_geom) AS y, extract(year FROM reportdate)*365+extract(doy FROM reportdate) AS t, age from cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes WHERE attributes.name='Serogroup' AND value='B' AND extract(year FROM reportdate)=2002")
    times <- dbGetResult(res) 
    months<-c("Jan","Feb","Mär","Apr","Mai","Jun","Jul","Aug","Sep","Okt","Nov","Dez")
    postscript("~/sero_b_2002.ps")
    prec <- 200
    b3d <- kernel3d(times, times$t, seq(min(times$x),max(times$x), length.out=prec),seq(min(times$y),max(times$y),length.out=prec),seq(min(times$t), max(times$t), length.out=12), 1.5, 30) 
    brks <- quantile(b3d$v, seq(0,1,0.05))
    cols <- heat.colors(length(brks)-1)
    oldpar <- par(mfrow=c(3,4))
    for (i in 1:12) image( seq(min(times$x),max(times$x),length.out=prec), seq(min(times$y),max(times$y),length.out=prec), b3d$v[,,i], asp=1.5, xlab="", ylab="", main=months[i], breaks=brks, col=cols)
    dev.off()
$BODY$
  LANGUAGE 'plr' VOLATILE STRICT;
ALTER FUNCTION episcangis_r_kernel2d() OWNER TO mreinhardt;
