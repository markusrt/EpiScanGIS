CREATE OR REPLACE FUNCTION episcangis_r_kernel2d()
  RETURNS void AS
$BODY$
    library(splancs)
    result <- pg.spi.exec ("SELECT X(the_geom) FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes WHERE attributes.name='Serogroup' AND value='B'")
    pts <- matrix(result,ncol=2)
    pdf("/tmp/myplot.pdf")
    plot(pts)
    #image(kernel2d(as.points(pts), pts$poly, h0=2, nx=100, ny=100), add=TRUE, col=terrain.colors(20))
    #pointmap(as.points(pts), add=TRUE)
    #polymap(pts$poly, add=TRUE)
    #pts.xy <- coordinates(pts[1:2])
    #apply(pts$poly, 2, range)
    #grd1 <- GridTopology(cellcentre.offset = c(-5.2, -11.5), cellsize = c(0.2, 0.2), cells.dim = c(75, 100))
    #k150 <- spkernel2d(pts.xy, pts$poly, h0=1.5, grd1)
    #k200 <- spkernel2d(pts.xy, pts$poly, h0=2, grd1)
    #k250 <- spkernel2d(pts.xy, pts$poly, h0=2.5, grd1)
    #kernels <-  SpatialGridDataFrame(grd1, data = AttributeList(list(k100 = k100, k150 = k150, k200 = k200, k250 = k250)))
    #spplot(kernels, checkEmptyRC = FALSE, col.regions = terrain.colors(16), cuts = 15)
    dev.off()
$BODY$
  LANGUAGE 'plr' VOLATILE STRICT;
ALTER FUNCTION episcangis_r_kernel2d() OWNER TO mreinhardt;
