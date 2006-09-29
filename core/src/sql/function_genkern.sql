CREATE OR REPLACE FUNCTION episcangis_r_genkern()
  RETURNS void AS
$BODY$
    x <- pg.spi.exec ("SELECT X(the_geom) FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes WHERE attributes.name='Serogroup' AND value='B'")
    y <- pg.spi.exec ("SELECT Y(the_geom) FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes  WHERE attributes.name='Serogroup' AND value='B'")
    pdf("/tmp/myplot.pdf")
    op <- KernSur(x, y, xgridsize=100, ygridsize=100, correlation=0, xbandwidth=0.7, ybandwidth=0.7, range.x=c(5,16), range.y=c(47,54))
    image(op$xords, op$yords, op$zden, col=terrain.colors(100), axes=TRUE, asp=2, xlab="Länge", ylab="Breite")
    contour(op$xords, op$yords, op$zden, add=TRUE)
    box()
    dev.off()
$BODY$
  LANGUAGE 'plr' VOLATILE STRICT;
ALTER FUNCTION r_test2() OWNER TO mreinhardt;
