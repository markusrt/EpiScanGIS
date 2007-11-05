--
-- PostgreSQL database dump
--

SET client_encoding = 'UNICODE';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: Max OID; Type: <Init>; Schema: -; Owner: 
--

CREATE TEMPORARY TABLE pgdump_oid (dummy integer) WITH OIDS;
COPY pgdump_oid WITH OIDS FROM stdin;
1642188	0
\.
DROP TABLE pgdump_oid;


--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


SET search_path = public, pg_catalog;

--
-- Name: plpgsql_call_handler(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plpgsql_call_handler() RETURNS language_handler
    AS '$libdir/plpgsql', 'plpgsql_call_handler'
    LANGUAGE c;


--
-- Name: plpgsql_validator(oid); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plpgsql_validator(oid) RETURNS void
    AS '$libdir/plpgsql', 'plpgsql_validator'
    LANGUAGE c;


--
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: public; Owner: 
--

CREATE TRUSTED PROCEDURAL LANGUAGE plpgsql HANDLER plpgsql_call_handler VALIDATOR plpgsql_validator;


--
-- Name: plr_call_handler(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plr_call_handler() RETURNS language_handler
    AS '$libdir/plr', 'plr_call_handler'
    LANGUAGE c;


--
-- Name: plr; Type: PROCEDURAL LANGUAGE; Schema: public; Owner: 
--

CREATE PROCEDURAL LANGUAGE plr HANDLER plr_call_handler;


--
-- Name: box2d_in(cstring); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_in(cstring) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_in'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_out(box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_out(box2d) RETURNS cstring
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_out'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE box2d (
    INTERNALLENGTH = 16,
    INPUT = box2d_in,
    OUTPUT = box2d_out,
    ALIGNMENT = int4,
    STORAGE = plain
);


--
-- Name: box3d_in(cstring); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box3d_in(cstring) RETURNS box3d
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_in'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box3d_out(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box3d_out(box3d) RETURNS cstring
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_out'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box3d; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE box3d (
    INTERNALLENGTH = 48,
    INPUT = box3d_in,
    OUTPUT = box3d_out,
    ALIGNMENT = double,
    STORAGE = plain
);


--
-- Name: chip_in(cstring); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION chip_in(cstring) RETURNS chip
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_in'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: chip_out(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION chip_out(chip) RETURNS cstring
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_out'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: chip; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE chip (
    INTERNALLENGTH = variable,
    INPUT = chip_in,
    OUTPUT = chip_out,
    ALIGNMENT = double,
    STORAGE = extended
);


--
-- Name: geometry_analyze(internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_analyze(internal) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_analyze'
    LANGUAGE c STRICT;


--
-- Name: geometry_in(cstring); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_in(cstring) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_in'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_out(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_out(geometry) RETURNS cstring
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_out'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_recv(internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_recv(internal) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_recv'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_send(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_send(geometry) RETURNS bytea
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_send'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE geometry (
    INTERNALLENGTH = variable,
    INPUT = geometry_in,
    OUTPUT = geometry_out,
    RECEIVE = geometry_recv,
    SEND = geometry_send,
    ANALYZE = geometry_analyze,
    DELIMITER = ':',
    ALIGNMENT = int4,
    STORAGE = main
);


--
-- Name: histogram2d_in(cstring); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION histogram2d_in(cstring) RETURNS histogram2d
    AS '$libdir/liblwgeom.so.1.1', 'lwhistogram2d_in'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: histogram2d_out(histogram2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION histogram2d_out(histogram2d) RETURNS cstring
    AS '$libdir/liblwgeom.so.1.1', 'lwhistogram2d_out'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: histogram2d; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE histogram2d (
    INTERNALLENGTH = variable,
    INPUT = histogram2d_in,
    OUTPUT = histogram2d_out,
    ALIGNMENT = double,
    STORAGE = main
);


--
-- Name: spheroid_in(cstring); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION spheroid_in(cstring) RETURNS spheroid
    AS '$libdir/liblwgeom.so.1.1', 'ellipsoid_in'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: spheroid_out(spheroid); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION spheroid_out(spheroid) RETURNS cstring
    AS '$libdir/liblwgeom.so.1.1', 'ellipsoid_out'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: spheroid; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE spheroid (
    INTERNALLENGTH = 65,
    INPUT = spheroid_in,
    OUTPUT = spheroid_out,
    ALIGNMENT = double,
    STORAGE = plain
);


--
-- Name: dtup; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE dtup AS (
	f1 text,
	f2 integer
);


--
-- Name: episcangis_attcount; Type: TYPE; Schema: public; Owner: mreinhardt
--

CREATE TYPE episcangis_attcount AS (
	count bigint,
	attvalue character varying
);


--
-- Name: geometry_dump; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE geometry_dump AS (
	path integer[],
	geom geometry
);


--
-- Name: mtup; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE mtup AS (
	f1 integer,
	f2 integer,
	f3 integer
);


--
-- Name: plr_environ_type; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE plr_environ_type AS (
	name text,
	value text
);


--
-- Name: r_typename; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE r_typename AS (
	typename text,
	typeoid oid
);


--
-- Name: vtup; Type: TYPE; Schema: public; Owner: admin
--

CREATE TYPE vtup AS (
	f1 integer
);


--
-- Name: addbbox(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION addbbox(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_addBBOX'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: addgeometrycolumn(character varying, character varying, character varying, character varying, integer, character varying, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION addgeometrycolumn(character varying, character varying, character varying, character varying, integer, character varying, integer) RETURNS text
    AS $_$
DECLARE
	catalog_name alias for $1;
	schema_name alias for $2;
	table_name alias for $3;
	column_name alias for $4;
	new_srid alias for $5;
	new_type alias for $6;
	new_dim alias for $7;
	rec RECORD;
	schema_ok bool;
	real_schema name;

BEGIN

	IF ( not ( (new_type ='GEOMETRY') or
		   (new_type ='GEOMETRYCOLLECTION') or
		   (new_type ='POINT') or 
		   (new_type ='MULTIPOINT') or
		   (new_type ='POLYGON') or
		   (new_type ='MULTIPOLYGON') or
		   (new_type ='LINESTRING') or
		   (new_type ='MULTILINESTRING') or
		   (new_type ='GEOMETRYCOLLECTIONM') or
		   (new_type ='POINTM') or 
		   (new_type ='MULTIPOINTM') or
		   (new_type ='POLYGONM') or
		   (new_type ='MULTIPOLYGONM') or
		   (new_type ='LINESTRINGM') or
		   (new_type ='MULTILINESTRINGM')) )
	THEN
		RAISE EXCEPTION 'Invalid type name - valid ones are: 
			GEOMETRY, GEOMETRYCOLLECTION, POINT, 
			MULTIPOINT, POLYGON, MULTIPOLYGON, 
			LINESTRING, MULTILINESTRING,
			GEOMETRYCOLLECTIONM, POINTM, 
			MULTIPOINTM, POLYGONM, MULTIPOLYGONM, 
			LINESTRINGM, or MULTILINESTRINGM ';
		return 'fail';
	END IF;

	IF ( (new_dim >4) or (new_dim <0) ) THEN
		RAISE EXCEPTION 'invalid dimension';
		return 'fail';
	END IF;

	IF ( (new_type LIKE '%M') and (new_dim!=3) ) THEN

		RAISE EXCEPTION 'TypeM needs 3 dimensions';
		return 'fail';
	END IF;

	IF ( schema_name != '' ) THEN
		schema_ok = 'f';
		FOR rec IN SELECT nspname FROM pg_namespace WHERE text(nspname) = schema_name LOOP
			schema_ok := 't';
		END LOOP;

		if ( schema_ok <> 't' ) THEN
			RAISE NOTICE 'Invalid schema name - using current_schema()';
			SELECT current_schema() into real_schema;
		ELSE
			real_schema = schema_name;
		END IF;

	ELSE
		SELECT current_schema() into real_schema;
	END IF;


	-- Add geometry column

	EXECUTE 'ALTER TABLE ' ||
		quote_ident(real_schema) || '.' || quote_ident(table_name)
		|| ' ADD COLUMN ' || quote_ident(column_name) || 
		' geometry ';


	-- Delete stale record in geometry_column (if any)

	EXECUTE 'DELETE FROM geometry_columns WHERE
		f_table_catalog = ' || quote_literal('') || 
		' AND f_table_schema = ' ||
		quote_literal(real_schema) || 
		' AND f_table_name = ' || quote_literal(table_name) ||
		' AND f_geometry_column = ' || quote_literal(column_name);


	-- Add record in geometry_column 

	EXECUTE 'INSERT INTO geometry_columns VALUES (' ||
		quote_literal('') || ',' ||
		quote_literal(real_schema) || ',' ||
		quote_literal(table_name) || ',' ||
		quote_literal(column_name) || ',' ||
		new_dim || ',' || new_srid || ',' ||
		quote_literal(new_type) || ')';

	-- Add table checks

	EXECUTE 'ALTER TABLE ' || 
		quote_ident(real_schema) || '.' || quote_ident(table_name)
		|| ' ADD CONSTRAINT ' 
		|| quote_ident('enforce_srid_' || column_name)
		|| ' CHECK (SRID(' || quote_ident(column_name) ||
		') = ' || new_srid || ')' ;

	EXECUTE 'ALTER TABLE ' || 
		quote_ident(real_schema) || '.' || quote_ident(table_name)
		|| ' ADD CONSTRAINT '
		|| quote_ident('enforce_dims_' || column_name)
		|| ' CHECK (ndims(' || quote_ident(column_name) ||
		') = ' || new_dim || ')' ;

	IF (not(new_type = 'GEOMETRY')) THEN
		EXECUTE 'ALTER TABLE ' || 
		quote_ident(real_schema) || '.' || quote_ident(table_name)
		|| ' ADD CONSTRAINT '
		|| quote_ident('enforce_geotype_' || column_name)
		|| ' CHECK (geometrytype(' ||
		quote_ident(column_name) || ')=' ||
		quote_literal(new_type) || ' OR (' ||
		quote_ident(column_name) || ') is null)';
	END IF;

	return 
		real_schema || '.' || 
		table_name || '.' || column_name ||
		' SRID:' || new_srid ||
		' TYPE:' || new_type || 
		' DIMS:' || new_dim || '
 '; 
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: addgeometrycolumn(character varying, character varying, character varying, integer, character varying, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION addgeometrycolumn(character varying, character varying, character varying, integer, character varying, integer) RETURNS text
    AS $_$
DECLARE
	ret  text;
BEGIN
	SELECT AddGeometryColumn('',$1,$2,$3,$4,$5,$6) into ret;
	RETURN ret;
END;
$_$
    LANGUAGE plpgsql STABLE STRICT;


--
-- Name: addgeometrycolumn(character varying, character varying, integer, character varying, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION addgeometrycolumn(character varying, character varying, integer, character varying, integer) RETURNS text
    AS $_$
DECLARE
	ret  text;
BEGIN
	SELECT AddGeometryColumn('','',$1,$2,$3,$4,$5) into ret;
	RETURN ret;
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: addpoint(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION addpoint(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_addpoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: addpoint(geometry, geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION addpoint(geometry, geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_addpoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: area(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION area(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_area_polygon'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: area2d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION area2d(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_area_polygon'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asbinary(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asbinary(geometry) RETURNS bytea
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asBinary'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asbinary(geometry, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asbinary(geometry, text) RETURNS bytea
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asBinary'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asewkb(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asewkb(geometry) RETURNS bytea
    AS '$libdir/liblwgeom.so.1.1', 'WKBFromLWGEOM'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asewkb(geometry, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asewkb(geometry, text) RETURNS bytea
    AS '$libdir/liblwgeom.so.1.1', 'WKBFromLWGEOM'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asewkt(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asewkt(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asEWKT'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asgml(geometry, integer, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asgml(geometry, integer, integer) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asGML'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asgml(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asgml(geometry, integer) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asGML'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: asgml(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION asgml(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asGML'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: assvg(geometry, integer, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION assvg(geometry, integer, integer) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'assvg_geometry'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: assvg(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION assvg(geometry, integer) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'assvg_geometry'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: assvg(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION assvg(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'assvg_geometry'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: astext(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION astext(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_asText'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: azimuth(geometry, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION azimuth(geometry, geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_azimuth'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: bdmpolyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION bdmpolyfromtext(text, integer) RETURNS geometry
    AS $_$
DECLARE
	geomtext alias for $1;
	srid alias for $2;
	mline geometry;
	geom geometry;
BEGIN
	mline := MultiLineStringFromText(geomtext, srid);

	IF mline IS NULL
	THEN
		RAISE EXCEPTION 'Input is not a MultiLinestring';
	END IF;

	geom := multi(BuildArea(mline));

	RETURN geom;
END;
$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: bdpolyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION bdpolyfromtext(text, integer) RETURNS geometry
    AS $_$
DECLARE
	geomtext alias for $1;
	srid alias for $2;
	mline geometry;
	geom geometry;
BEGIN
	mline := MultiLineStringFromText(geomtext, srid);

	IF mline IS NULL
	THEN
		RAISE EXCEPTION 'Input is not a MultiLinestring';
	END IF;

	geom := BuildArea(mline);

	IF GeometryType(geom) != 'POLYGON'
	THEN
		RAISE EXCEPTION 'Input returns more then a single polygon, try using BdMPolyFromText instead';
	END IF;

	RETURN geom;
END;
$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: boundary(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION boundary(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'boundary'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box(geometry) RETURNS box
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_to_BOX'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box(box3d) RETURNS box
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_to_BOX'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d(geometry) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_to_BOX2DFLOAT4'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d(box3d) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_to_BOX2DFLOAT4'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_contain(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_contain(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_contain'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_contained(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_contained(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_contained'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_intersects(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_intersects(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_intersects'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_left(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_left(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_left'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_overlap(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_overlap(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_overlap'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_overleft(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_overleft(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_overleft'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_overright(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_overright(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_overright'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_right(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_right(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_right'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box2d_same(box2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box2d_same(box2d, box2d) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'BOX2D_same'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box3d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box3d(geometry) RETURNS box3d
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_to_BOX3D'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box3d(box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box3d(box2d) RETURNS box3d
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_to_BOX3D'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: box3dtobox(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION box3dtobox(box3d) RETURNS box
    AS $_$SELECT box($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: buffer(geometry, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION buffer(geometry, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'buffer'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: buffer(geometry, double precision, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION buffer(geometry, double precision, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'buffer'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: build_histogram2d(histogram2d, text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION build_histogram2d(histogram2d, text, text) RETURNS histogram2d
    AS '$libdir/liblwgeom.so.1.1', 'build_lwhistogram2d'
    LANGUAGE c STABLE STRICT;


--
-- Name: build_histogram2d(histogram2d, text, text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION build_histogram2d(histogram2d, text, text, text) RETURNS histogram2d
    AS $_$
BEGIN
	EXECUTE 'SET local search_path = '||$2||',public';
	RETURN public.build_histogram2d($1,$3,$4);
END
$_$
    LANGUAGE plpgsql STABLE STRICT;


--
-- Name: buildarea(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION buildarea(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_buildarea'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: bytea(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION bytea(geometry) RETURNS bytea
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_to_bytea'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: cache_bbox(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION cache_bbox() RETURNS "trigger"
    AS '$libdir/liblwgeom.so.1.1', 'cache_bbox'
    LANGUAGE c;


--
-- Name: centroid(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION centroid(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'centroid'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: collect(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION collect(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_collect'
    LANGUAGE c IMMUTABLE;


--
-- Name: collect_garray(geometry[]); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION collect_garray(geometry[]) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_collect_garray'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: collector(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION collector(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_collect'
    LANGUAGE c IMMUTABLE;


--
-- Name: combine_bbox(box2d, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION combine_bbox(box2d, geometry) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_combine'
    LANGUAGE c IMMUTABLE;


--
-- Name: combine_bbox(box3d, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION combine_bbox(box3d, geometry) RETURNS box3d
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_combine'
    LANGUAGE c IMMUTABLE;


--
-- Name: compression(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION compression(chip) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_getCompression'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: contains(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION contains(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'contains'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: convexhull(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION convexhull(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'convexhull'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: create_histogram2d(box2d, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION create_histogram2d(box2d, integer) RETURNS histogram2d
    AS '$libdir/liblwgeom.so.1.1', 'create_lwhistogram2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: crosses(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION crosses(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'crosses'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: datatype(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION datatype(chip) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_getDatatype'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: difference(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION difference(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'difference'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: dimension(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dimension(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_dimension'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: disjoint(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION disjoint(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'disjoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: distance(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION distance(geometry, geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_mindistance2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: distance_sphere(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION distance_sphere(geometry, geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_distance_sphere'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: distance_spheroid(geometry, geometry, spheroid); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION distance_spheroid(geometry, geometry, spheroid) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_distance_ellipsoid_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: dropbbox(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropbbox(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_dropBBOX'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: dropgeometrycolumn(character varying, character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropgeometrycolumn(character varying, character varying, character varying, character varying) RETURNS text
    AS $_$
DECLARE
	catalog_name alias for $1; 
	schema_name alias for $2;
	table_name alias for $3;
	column_name alias for $4;
	myrec RECORD;
	okay boolean;
	real_schema name;

BEGIN


	-- Find, check or fix schema_name
	IF ( schema_name != '' ) THEN
		okay = 'f';

		FOR myrec IN SELECT nspname FROM pg_namespace WHERE text(nspname) = schema_name LOOP
			okay := 't';
		END LOOP;

		IF ( okay <> 't' ) THEN
			RAISE NOTICE 'Invalid schema name - using current_schema()';
			SELECT current_schema() into real_schema;
		ELSE
			real_schema = schema_name;
		END IF;
	ELSE
		SELECT current_schema() into real_schema;
	END IF;

 	-- Find out if the column is in the geometry_columns table
	okay = 'f';
	FOR myrec IN SELECT * from geometry_columns where f_table_schema = text(real_schema) and f_table_name = table_name and f_geometry_column = column_name LOOP
		okay := 't';
	END LOOP; 
	IF (okay <> 't') THEN 
		RAISE EXCEPTION 'column not found in geometry_columns table';
		RETURN 'f';
	END IF;

	-- Remove ref from geometry_columns table
	EXECUTE 'delete from geometry_columns where f_table_schema = ' ||
		quote_literal(real_schema) || ' and f_table_name = ' ||
		quote_literal(table_name)  || ' and f_geometry_column = ' ||
		quote_literal(column_name);
	
	-- Remove table column
	EXECUTE 'ALTER TABLE ' || quote_ident(real_schema) || '.' ||
		quote_ident(table_name) || ' DROP COLUMN ' ||
		quote_ident(column_name);


	RETURN real_schema || '.' || table_name || '.' || column_name ||' effectively removed.';
	
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: dropgeometrycolumn(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropgeometrycolumn(character varying, character varying, character varying) RETURNS text
    AS $_$
DECLARE
	ret text;
BEGIN
	SELECT DropGeometryColumn('',$1,$2,$3) into ret;
	RETURN ret;
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: dropgeometrycolumn(character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropgeometrycolumn(character varying, character varying) RETURNS text
    AS $_$
DECLARE
	ret text;
BEGIN
	SELECT DropGeometryColumn('','',$1,$2) into ret;
	RETURN ret;
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: dropgeometrytable(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropgeometrytable(character varying, character varying, character varying) RETURNS text
    AS $_$
DECLARE
	catalog_name alias for $1; 
	schema_name alias for $2;
	table_name alias for $3;
	real_schema name;

BEGIN

	IF ( schema_name = '' ) THEN
		SELECT current_schema() into real_schema;
	ELSE
		real_schema = schema_name;
	END IF;

	-- Remove refs from geometry_columns table
	EXECUTE 'DELETE FROM geometry_columns WHERE ' ||
		'f_table_schema = ' || quote_literal(real_schema) ||
		' AND ' ||
		' f_table_name = ' || quote_literal(table_name);
	
	-- Remove table 
	EXECUTE 'DROP TABLE '
		|| quote_ident(real_schema) || '.' ||
		quote_ident(table_name);

	RETURN
		real_schema || '.' ||
		table_name ||' dropped.';
	
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: dropgeometrytable(character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropgeometrytable(character varying, character varying) RETURNS text
    AS $_$SELECT DropGeometryTable('',$1,$2)$_$
    LANGUAGE sql STRICT;


--
-- Name: dropgeometrytable(character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dropgeometrytable(character varying) RETURNS text
    AS $_$SELECT DropGeometryTable('','',$1)$_$
    LANGUAGE sql STRICT;


--
-- Name: dump(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION dump(geometry) RETURNS SETOF geometry_dump
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_dump'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: endpoint(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION endpoint(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_endpoint_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: envelope(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION envelope(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_envelope'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: epidegis_nrzm_import(character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION epidegis_nrzm_import(filename character varying) RETURNS integer
    AS $$
DECLARE
  pg_class_row pg_class%ROWTYPE;
BEGIN
  -- Check if import table exists
  SELECT * INTO pg_class_row FROM pg_class WHERE relname='nrzm_imprt';
  IF pg_class_row.relname IS NULL THEN
    RAISE EXCEPTION 'Import failed, table ''nrzm_imprt'' does not exits.';
  ELSE
    -- Clear import table
    DELETE FROM nrzm_imprt; 
    -- Import CSV file
    EXECUTE 'COPY nrzm_imprt FROM ''' || filename || ''' WITH DELIMITER '';''';
    -- Insert new casetypes in table (with default timestamp=now)
    INSERT INTO case_types (identifier) (
      SELECT concat_type FROM (
        SELECT DISTINCT (sg || ';' || vr1|| ';' || vr2|| ';' || feta) as concat_type FROM nrzm_imprt
      ) AS distinct_types  
      WHERE concat_type NOT IN (SELECT identifier FROM case_types)
    );
    -- Refresh case_types_attributes
    DELETE FROM case_type_attributes;
    INSERT INTO case_type_attributes (case_type_id, attribute_id, value) (
      SELECT ct.case_type_id, a.attribute_id, split_part(ct.identifier,';',1) 
        FROM case_types ct, attributes a WHERE a.name='Serogroup'
    );
    -- Refresh cases table
    DELETE FROM cases;
    INSERT INTO cases (
      SELECT 
        pnr,
        CASE WHEN altr='' THEN
          -1
        ELSE
          CAST( altr AS int2 )
        END AS altr,
        '?',
        --CAST( geschlecht AS char ),
        CASE WHEN eingang='' THEN
          NULL
        ELSE
	        to_date(eingang, 'DD.MM.YYYY')
        END AS eingang,
        CASE WHEN entnahme='' THEN
          to_date(eingang, 'DD.MM.YYYY')
        ELSE
          to_date(entnahme, 'DD.MM.YYYY')
        END AS entnahme,
        case_type_id,
        GeometryFromText('POINT(' || replace(lon,',','.') || ' ' || replace(lat,',','.') || ')',4326)
      FROM nrzm_imprt, case_types
      WHERE (sg || ';' || vr1|| ';' || vr2|| ';' || feta)=case_types.identifier
    );
    -- create the table contains_area_case
    -- this will speedup incidence calculations as contains() is very slow
    SELECT * INTO pg_class_row FROM pg_class WHERE relname='contains_area_case';
    IF pg_class_row.relname IS NOT NULL THEN
      DROP TABLE contains_area_case;
    END IF;
    CREATE TABLE contains_area_case AS
    SELECT area_id, case_id
      FROM areas, cases 
      WHERE areas.the_geom && cases.the_geom 
        AND contains(areas.the_geom, cases.the_geom);
  END IF;
  RETURN 0;
END
$$
    LANGUAGE plpgsql;


--
-- Name: episcangis_cases_per_attribute_value(integer, integer, integer, integer, integer, integer, character varying, character varying[]); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_cases_per_attribute_value(frommonth integer, fromyear integer, tomonth integer, toyear integer, fromage integer, toage integer, attname character varying, "values" character varying[]) RETURNS SETOF record
    AS $_$
  SELECT case_id AS oid, the_geom, attvalue AS serogroup, reportdate
    FROM cases_random NATURAL JOIN types_with_attributes
    WHERE 
      CAST(to_char(reportdate, 'YYYY') AS int) * 12 
      + CAST(to_char(reportdate, 'MM') AS int)
    BETWEEN $2*12+$1 AND $4*12+$3
          AND attname = $7 
          AND attvalue = ANY ($8)
          AND age BETWEEN $5 AND $6
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_cases_per_cluster(bigint); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_cases_per_cluster(cluster_id bigint) RETURNS bigint
    AS $_$
  SELECT count(*) AS count
      FROM cases_random AS c, satscan_clusters AS sc, satscan_cluster_cases AS scc,
        case_types AS ct
      WHERE c.case_type_id = ct.case_type_id
        AND sc.satscan_cluster_id = scc.satscan_cluster_id
        AND c.case_id = scc.case_id
        AND sc.satscan_cluster_id = $1
        GROUP BY sc.satscan_cluster_id
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_count_cases_attribute_year(character varying, smallint); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_count_cases_attribute_year(attribute character varying, "year" smallint) RETURNS SETOF episcangis_attcount
    AS $_$
SELECT count(*), value
 FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes 
   NATURAL JOIN attributes
 WHERE attributes.name=$1 AND value!=''
 AND date_part('year', reportdate) = $2
 GROUP BY value
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_count_cases_attribute_year(character varying, integer); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_count_cases_attribute_year(attribute character varying, "year" integer) RETURNS SETOF episcangis_attcount
    AS $_$
SELECT count(*), value
 FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes 
   NATURAL JOIN attributes
 WHERE attributes.name=$1 AND value!=''
 AND date_part('year', reportdate) = $2
 GROUP BY value
 ORDER BY count(*) DESC
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_count_cases_per_area_attribute(character varying, smallint); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_count_cases_per_area_attribute(character varying, smallint) RETURNS SETOF record
    AS $_$
 SELECT 0 AS count, value, identifier 
  FROM areas_with_types AS awt, case_type_attributes NATURAL JOIN attributes
  WHERE tier=$2 AND  attributes.name=$1 AND value!=''
  AND (value,identifier) NOT IN
   (
     SELECT value, areas.identifier
      FROM areas NATURAL JOIN area_types, contains_area_case AS cac, 
        cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes 
        NATURAL JOIN attributes
      WHERE attributes.name=$1 AND value!=''
        AND cac.area_id=areas.area_id
        AND cac.case_id=cases.case_id
        AND tier=$2
      GROUP BY value, areas.identifier
    )
   GROUP BY value, awt.identifier
UNION   
SELECT count(*), value, areas.identifier
 FROM areas NATURAL JOIN area_types, contains_area_case AS cac, 
   cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes 
   NATURAL JOIN attributes
 WHERE attributes.name=$1 AND value!=''
   AND cac.area_id=areas.area_id
   AND cac.case_id=cases.case_id
   AND tier=$2
 GROUP BY value, areas.identifier
    
ORDER BY identifier,value
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_incidence_finetype_per_area(integer, integer, integer, integer, character varying, integer); Type: FUNCTION; Schema: public; Owner: episcangis_admin
--

CREATE FUNCTION episcangis_incidence_finetype_per_area(frommonth integer, fromyear integer, tomonth integer, toyear integer, finetype character varying, areatier integer) RETURNS SETOF record
    AS $_$  SELECT DISTINCT ON (oid) oid, the_geom, incidence, identifier
  FROM (
    SELECT awt.area_id AS oid, awt.the_geom, 
          CASE
            WHEN (($4*12+$3)-($2*12+$1)+1)::real<=12 THEN
              count(*)::real/(awt.population::real/100000::real)
          ELSE
            count(*)::real/(awt.population::real/100000::real)*
              ( 12/(($4*12+$3)-($2*12+$1)+1)::real)::real 
          END AS incidence,
          awt.identifier 
        FROM contains_area_case, case_types, areas_with_types AS awt, cases
        WHERE contains_area_case.area_id=awt.area_id 
          AND contains_area_case.case_id=cases.case_id
          AND cases.case_type_id = case_types.case_type_id
          AND case_types.identifier IN ($5)
          AND CAST(to_char(reportdate, 'YYYY') AS int) * 12 
               + CAST(to_char(reportdate, 'MM') AS int)
              BETWEEN $2*12+$1 
                AND $4*12+$3
          AND awt.tier=$6
        GROUP BY awt.area_id, awt.the_geom, population, awt.identifier
      UNION
      SELECT awt.area_id AS oid, awt.the_geom, 0 AS incidence, awt.identifier
        FROM areas_with_types AS awt
        WHERE awt.tier=$6
        ORDER BY incidence DESC
) AS sub
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_incidence_per_area(integer, integer, integer, integer, character varying[], integer); Type: FUNCTION; Schema: public; Owner: episcangis_admin
--

CREATE FUNCTION episcangis_incidence_per_area(frommonth integer, fromyear integer, tomonth integer, toyear integer, serogroups character varying[], areatier integer) RETURNS SETOF record
    AS $_$  SELECT DISTINCT ON (oid) oid, the_geom, incidence
  FROM (
    SELECT awt.area_id AS oid, awt.the_geom, 
          CASE
            WHEN (($4*12+$3)-($2*12+$1)+1)::real<=12 THEN
              count(*)::real/(awt.population::real/100000::real)
          ELSE
            count(*)::real/(awt.population::real/100000::real)*
              ( 12/(($4*12+$3)-($2*12+$1)+1)::real)::real 
          END AS incidence 
        FROM contains_area_case, areas_with_types AS awt, cases
        WHERE contains_area_case.area_id=awt.area_id 
          AND contains_area_case.case_id=cases.case_id
          AND case_type_id = types_with_attributes.case_type_id
          AND types_with_attributes.attname LIKE 'Serogroup'
          AND types_with_attributes.attvalue = ANY ($5)
          AND CAST(to_char(reportdate, 'YYYY') AS int) * 12 
               + CAST(to_char(reportdate, 'MM') AS int)
              BETWEEN $2*12+$1 
                AND $4*12+$3
          AND awt.tier=$6
        GROUP BY awt.area_id, awt.the_geom, population
      UNION
      SELECT awt.area_id AS oid, awt.the_geom, 0 AS incidence
        FROM areas_with_types AS awt
        WHERE awt.tier=$6
        ORDER BY incidence DESC
) AS sub
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_nrzm_import(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION episcangis_nrzm_import() RETURNS integer
    AS $$
DECLARE
  pg_class_row pg_class%ROWTYPE;
BEGIN
  -- Check if import table exists
  SELECT * INTO pg_class_row FROM pg_class WHERE relname='nrzm_imprt';
  IF pg_class_row.relname IS NULL THEN
    RAISE EXCEPTION 'Import failed, table ''nrzm_imprt'' does not exits.';
  ELSE
    -- Insert new casetypes in table (with default timestamp=now)
    INSERT INTO case_types (identifier) (
      SELECT concat_type FROM (
        SELECT DISTINCT (sg || ';' || vr1|| ';' || vr2|| ';' || feta) as concat_type FROM nrzm_imprt
      ) AS distinct_types  
      WHERE concat_type NOT IN (SELECT identifier FROM case_types)
    );
    -- Refresh case_types_attributes
    DELETE FROM case_type_attributes;
    INSERT INTO case_type_attributes (case_type_id, attribute_id, value) (
      SELECT ct.case_type_id, a.attribute_id, split_part(ct.identifier,';',1) 
        FROM case_types ct, attributes a WHERE a.name='Serogroup'
    );
    -- Refresh cases table
    DELETE FROM cases WHERE case_id IN (SELECT pnr FROM nrzm_imprt);
    INSERT INTO cases (
      SELECT 
        pnr,
        CASE WHEN altr='' THEN
          -1
        ELSE
          CAST( altr AS int2 )
        END AS altr,
        '?',
        --CAST( geschlecht AS char ),
        CASE WHEN eingang='' THEN
          NULL
        ELSE
          CAST( ( split_part(eingang, '.', 2) || '/' ||
                  split_part(eingang, '.', 1) || '/' ||
                  split_part(eingang, '.', 3) ) AS date )
        END AS eingang,
        CASE WHEN entnahme='' THEN
          CAST( ( split_part(eingang, '.', 2) || '/' ||
                  split_part(eingang, '.', 1) || '/' ||
                  split_part(eingang, '.', 3) ) AS date )
        ELSE
          CAST( ( split_part(entnahme, '.', 2) || '/' ||
                  split_part(entnahme, '.', 1) || '/' ||
                  split_part(entnahme, '.', 3) ) AS date )
        END AS entnahme,
        case_type_id,
        GeometryFromText('POINT(' || replace(lon,',','.') || ' ' || replace(lat,',','.') || ')',4326)
      FROM nrzm_imprt, case_types
      WHERE (sg || ';' || vr1|| ';' || vr2|| ';' || feta)=case_types.identifier
    );
    -- create the table contains_area_case
    -- this will speedup incidence calculations as contains() is very slow
    SELECT * INTO pg_class_row FROM pg_class WHERE relname='contains_area_case';
    --IF pg_class_row.relname IS NOT NULL THEN
    --  DROP TABLE contains_area_case;
    --END IF;
    --CREATE TABLE contains_area_case AS
    DELETE FROM contains_area_case;
    INSERT INTO contains_area_case (
      SELECT area_id, case_id
        FROM areas, cases 
        WHERE areas.the_geom && cases.the_geom 
          AND contains(areas.the_geom, cases.the_geom)
    );
  END IF;
  RETURN 0;
END
$$
    LANGUAGE plpgsql;


--
-- Name: episcangis_population_per_cluster(bigint); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_population_per_cluster(cluster_id bigint) RETURNS numeric
    AS $_$
  SELECT sum(pop) FROM (SELECT ap.population AS pop FROM satscan_cluster_cases AS scc, contains_area_case AS cac, areas AS a, area_populations AS ap
      WHERE scc.satscan_cluster_id = $1
        AND cac.case_id=scc.case_id
        AND cac.area_id=a.area_id
        AND a.area_type_id=5
        AND a.area_id=ap.area_id
        AND ap.year=2005
      GROUP BY a.area_id, ap.population) AS sub
$_$
    LANGUAGE sql STABLE;


--
-- Name: episcangis_r_kernel2d(); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_r_kernel2d() RETURNS text
    AS $_$
    library(splancs)
    result <- pg.spi.exec ("SELECT X(the_geom)::float4 AS x FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes WHERE attributes.name='Serogroup' AND value='B'")
    print(typeof(result[1:1,1:1]))
    plot(result)
    #pts <- matrix(result,ncol=2)
    pdf("/tmp/myplot.pdf")
    #plot(pts[1:1,1:1])
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
$_$
    LANGUAGE plr STRICT;


--
-- Name: episcangis_r_kernel3d(); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION episcangis_r_kernel3d() RETURNS text
    AS $_$
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
$_$
    LANGUAGE plr STRICT;


--
-- Name: equals(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION equals(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'geomequals'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: estimate_histogram2d(histogram2d, box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION estimate_histogram2d(histogram2d, box2d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'estimate_lwhistogram2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: estimated_extent(text, text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION estimated_extent(text, text, text) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_estimated_extent'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: estimated_extent(text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION estimated_extent(text, text) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_estimated_extent'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: expand(box3d, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION expand(box3d, double precision) RETURNS box3d
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_expand'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: expand(box2d, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION expand(box2d, double precision) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_expand'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: expand(geometry, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION expand(geometry, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_expand'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: explode_histogram2d(histogram2d, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION explode_histogram2d(histogram2d, text) RETURNS histogram2d
    AS '$libdir/liblwgeom.so.1.1', 'explode_lwhistogram2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: exteriorring(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION exteriorring(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_exteriorring_polygon'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: factor(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION factor(chip) RETURNS real
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_getFactor'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: find_extent(text, text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION find_extent(text, text, text) RETURNS box2d
    AS $_$
DECLARE
	schemaname alias for $1;
	tablename alias for $2;
	columnname alias for $3;
	myrec RECORD;

BEGIN
	FOR myrec IN EXECUTE 'SELECT extent("'||columnname||'") FROM "'||schemaname||'"."'||tablename||'"' LOOP
		return myrec.extent;
	END LOOP; 
END;
$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: find_extent(text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION find_extent(text, text) RETURNS box2d
    AS $_$
DECLARE
	tablename alias for $1;
	columnname alias for $2;
	myrec RECORD;

BEGIN
	FOR myrec IN EXECUTE 'SELECT extent("'||columnname||'") FROM "'||tablename||'"' LOOP
		return myrec.extent;
	END LOOP; 
END;
$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: find_srid(character varying, character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION find_srid(character varying, character varying, character varying) RETURNS integer
    AS $_$DECLARE
   schem text;
   tabl text;
   sr int4;
BEGIN
   IF $1 IS NULL THEN
      RAISE EXCEPTION 'find_srid() - schema is NULL!';
   END IF;
   IF $2 IS NULL THEN
      RAISE EXCEPTION 'find_srid() - table name is NULL!';
   END IF;
   IF $3 IS NULL THEN
      RAISE EXCEPTION 'find_srid() - column name is NULL!';
   END IF;
   schem = $1;
   tabl = $2;
-- if the table contains a . and the schema is empty
-- split the table into a schema and a table
-- otherwise drop through to default behavior
   IF ( schem = '' and tabl LIKE '%.%' ) THEN
     schem = substr(tabl,1,strpos(tabl,'.')-1);
     tabl = substr(tabl,length(schem)+2);
   ELSE
     schem = schem || '%';
   END IF;

   select SRID into sr from geometry_columns where f_table_schema like schem and f_table_name = tabl and f_geometry_column = $3;
   IF NOT FOUND THEN
       RAISE EXCEPTION 'find_srid() - couldnt find the corresponding SRID - is the geometry registered in the GEOMETRY_COLUMNS table?  Is there an uppercase/lowercase missmatch?';
   END IF;
  return sr;
END;
$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: fix_geometry_columns(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION fix_geometry_columns() RETURNS text
    AS $$
DECLARE
	mislinked record;
	result text;
	linked integer;
	deleted integer;
	foundschema integer;
BEGIN

	-- Since 7.3 schema support has been added.
	-- Previous postgis versions used to put the database name in
	-- the schema column. This needs to be fixed, so we try to 
	-- set the correct schema for each geometry_colums record
	-- looking at table, column, type and srid.
	UPDATE geometry_columns SET f_table_schema = n.nspname
		FROM pg_namespace n, pg_class c, pg_attribute a,
			pg_constraint sridcheck, pg_constraint typecheck
                WHERE ( f_table_schema is NULL
		OR f_table_schema = ''
                OR f_table_schema NOT IN (
                        SELECT nspname::varchar
                        FROM pg_namespace nn, pg_class cc, pg_attribute aa
                        WHERE cc.relnamespace = nn.oid
                        AND cc.relname = f_table_name::name
                        AND aa.attrelid = cc.oid
                        AND aa.attname = f_geometry_column::name))
                AND f_table_name::name = c.relname
                AND c.oid = a.attrelid
                AND c.relnamespace = n.oid
                AND f_geometry_column::name = a.attname

                AND sridcheck.conrelid = c.oid
		AND sridcheck.consrc LIKE '(srid(% = %)'
                AND sridcheck.consrc ~ textcat(' = ', srid::text)

                AND typecheck.conrelid = c.oid
		AND typecheck.consrc LIKE
	'((geometrytype(%) = ''%''::text) OR (% IS NULL))'
                AND typecheck.consrc ~ textcat(' = ''', type::text)

                AND NOT EXISTS (
                        SELECT oid FROM geometry_columns gc
                        WHERE c.relname::varchar = gc.f_table_name
                        AND n.nspname::varchar = gc.f_table_schema
                        AND a.attname::varchar = gc.f_geometry_column
                );

	GET DIAGNOSTICS foundschema = ROW_COUNT;

	-- no linkage to system table needed
	return 'fixed:'||foundschema::text;

	-- fix linking to system tables
	SELECT 0 INTO linked;
	FOR mislinked in
		SELECT gc.oid as gcrec,
			a.attrelid as attrelid, a.attnum as attnum
                FROM geometry_columns gc, pg_class c,
		pg_namespace n, pg_attribute a
                WHERE ( gc.attrelid IS NULL OR gc.attrelid != a.attrelid 
			OR gc.varattnum IS NULL OR gc.varattnum != a.attnum)
                AND n.nspname = gc.f_table_schema::name
                AND c.relnamespace = n.oid
                AND c.relname = gc.f_table_name::name
                AND a.attname = f_geometry_column::name
                AND a.attrelid = c.oid
	LOOP
		UPDATE geometry_columns SET
			attrelid = mislinked.attrelid,
			varattnum = mislinked.attnum,
			stats = NULL
			WHERE geometry_columns.oid = mislinked.gcrec;
		SELECT linked+1 INTO linked;
	END LOOP; 

	-- remove stale records
	DELETE FROM geometry_columns WHERE attrelid IS NULL;

	GET DIAGNOSTICS deleted = ROW_COUNT;

	result = 
		'fixed:' || foundschema::text ||
		' linked:' || linked::text || 
		' deleted:' || deleted::text;

	return result;

END;
$$
    LANGUAGE plpgsql;


--
-- Name: force_2d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION force_2d(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: force_3d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION force_3d(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_3dz'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: force_3dm(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION force_3dm(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_3dm'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: force_3dz(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION force_3dz(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_3dz'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: force_4d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION force_4d(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_4d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: force_collection(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION force_collection(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_collection'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: forcerhr(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION forcerhr(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_forceRHR_poly'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geom_accum(geometry[], geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geom_accum(geometry[], geometry) RETURNS geometry[]
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_accum'
    LANGUAGE c IMMUTABLE;


--
-- Name: geomcollfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomcollfromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromText($1, $2)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geomcollfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomcollfromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromText($1)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geomcollfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomcollfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromWKB($1, $2)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geomcollfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomcollfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromWKB($1)) = 'GEOMETRYCOLLECTION'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geometry(box2d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry(box2d) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_to_LWGEOM'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry(box3d) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_to_LWGEOM'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry(text) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'parse_WKT_lwgeom'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry(chip) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_to_LWGEOM'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry(bytea) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_from_bytea'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_above(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_above(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_above'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_below(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_below(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_below'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_cmp(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_cmp(geometry, geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'lwgeom_cmp'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_contain(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_contain(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_contain'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_contained(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_contained(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_contained'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_eq(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_eq(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'lwgeom_eq'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_filer(geometry, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_filer(geometry, text) RETURNS geometry
    AS $_$DECLARE

  shp ALIAS for $1;

  gtype ALIAS for $2;

  gshp GEOMETRY;

  i integer;

  j integer;



  ishp GEOMETRY;

  exshp GEOMETRY;  

  c_shp  GEOMETRY;

  m_shp GEOMETRY;

BEGIN



   IF ( geometrytype(shp) = gtype) THEN

     return shp;

   END IF;

   

   IF ( geometrytype(shp) = 'POLYGON' ) THEN

     ishp = replace(astext(shp),'POLYGON(','MULTIPOLYGON((') || ')';     

   ELSE

     IF ( geometrytype(shp) = 'LINESTRING' ) THEN

       ishp = replace(astext(shp),'LINESTRING(','MULTILINESTRING((') || ')';   

     ELSE

       ishp = shp;

     END IF;

   END IF; 



   IF ( (numgeometries(ishp) is NULL) ) THEN

       return NULL;

   END IF;



  

   c_shp = NULL;


   FOR j IN 1..(numgeometries(ishp)) LOOP



     gshp = geometryn(ishp,j); 

     IF ( geometrytype(gshp) = 'POLYGON' ) THEN

       gshp = replace(astext(gshp),'POLYGON(','MULTIPOLYGON((') || ')';

     END IF;

     IF ( (geometrytype(shp) = 'LINESTRING') and (startpoint(shp) = endpoint(shp)) ) THEN

       ishp = replace(astext(shp),'LINESTRING(','MULTIPOLYGON(((') || '))';   

     END IF;

 

     IF ( (geometrytype(gshp) = gtype ) ) THEN

       IF ( c_shp is not NULL ) THEN

          c_shp = geometry_union(c_shp,gshp);

       ELSE

          c_shp = gshp;

       END IF;

     END IF;

   END LOOP;

  


  IF ( geometrytype(c_shp) = 'POLYGON' ) THEN

     c_shp = replace(astext(c_shp),'POLYGON(','MULTIPOLYGON((') || ')';

   END IF;

 

   RETURN c_shp;



END;$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: geometry_ge(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_ge(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'lwgeom_ge'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_gt(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_gt(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'lwgeom_gt'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_le(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_le(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'lwgeom_le'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_left(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_left(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_left'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_lt(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_lt(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'lwgeom_lt'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_overabove(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_overabove(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_overabove'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_overbelow(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_overbelow(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_overbelow'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_overlap(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_overlap(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_overlap'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_overleft(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_overleft(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_overleft'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_overright(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_overright(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_overright'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_right(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_right(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_right'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometry_same(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometry_same(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_same'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometryfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometryfromtext(text) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_from_text'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometryfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometryfromtext(text, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_from_text'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometryn(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometryn(geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_geometryn_collection'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geometrytype(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geometrytype(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_getTYPE'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geomfromewkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomfromewkb(bytea) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOMFromWKB'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geomfromewkt(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomfromewkt(text) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'parse_WKT_lwgeom'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geomfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomfromtext(text) RETURNS geometry
    AS $_$SELECT geometryfromtext($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geomfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomfromtext(text, integer) RETURNS geometry
    AS $_$SELECT geometryfromtext($1, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geomfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomfromwkb(bytea) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_from_WKB'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geomfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomfromwkb(bytea, integer) RETURNS geometry
    AS $_$SELECT setSRID(GeomFromWKB($1), $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: geomunion(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geomunion(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'geomunion'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: geosnoop(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION geosnoop(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'GEOSnoop'
    LANGUAGE c STRICT;


--
-- Name: get_proj4_from_srid(integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION get_proj4_from_srid(integer) RETURNS text
    AS $_$
BEGIN
	RETURN proj4text::text FROM spatial_ref_sys WHERE srid= $1;
END;
$_$
    LANGUAGE plpgsql IMMUTABLE STRICT;


--
-- Name: getbbox(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION getbbox(geometry) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_to_BOX2DFLOAT4'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: getsrid(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION getsrid(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_getSRID'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: hasbbox(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION hasbbox(geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_hasBBOX'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: height(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION height(chip) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_getHeight'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: install_rcmd(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION install_rcmd(text) RETURNS text
    AS '$libdir/plr', 'install_rcmd'
    LANGUAGE c STRICT;


--
-- Name: interiorringn(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION interiorringn(geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_interiorringn_polygon'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: intersection(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION intersection(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'intersection'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: intersects(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION intersects(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'intersects'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: isclosed(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION isclosed(geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_isclosed_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: isempty(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION isempty(geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_isempty'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: isring(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION isring(geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'isring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: issimple(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION issimple(geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'issimple'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: isvalid(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION isvalid(geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'isvalid'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: jtsnoop(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION jtsnoop(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'JTSnoop'
    LANGUAGE c STRICT;


--
-- Name: length(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION length(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_length_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: length2d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION length2d(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_length2d_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: length2d_spheroid(geometry, spheroid); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION length2d_spheroid(geometry, spheroid) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_length2d_ellipsoid_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: length3d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION length3d(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_length_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: length3d_spheroid(geometry, spheroid); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION length3d_spheroid(geometry, spheroid) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_length_ellipsoid_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: length_spheroid(geometry, spheroid); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION length_spheroid(geometry, spheroid) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_length_ellipsoid_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: line_interpolate_point(geometry, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION line_interpolate_point(geometry, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_line_interpolate_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: line_locate_point(geometry, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION line_locate_point(geometry, geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_line_locate_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: line_substring(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION line_substring(geometry, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_line_substring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: linefrommultipoint(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linefrommultipoint(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_line_from_mpoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: linefromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linefromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'LINESTRING'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linefromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linefromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'LINESTRING'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linefromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linefromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'LINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linefromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linefromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'LINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linemerge(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION linemerge(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'linemerge'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: linestringfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linestringfromtext(text) RETURNS geometry
    AS $_$SELECT LineFromText($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linestringfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linestringfromtext(text, integer) RETURNS geometry
    AS $_$SELECT LineFromText($1, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linestringfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linestringfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'LINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: linestringfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION linestringfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'LINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: load_r_typenames(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION load_r_typenames() RETURNS text
    AS $$
  sql <- "select upper(typname::text) || 'OID' as typename, oid from pg_catalog.pg_type where typtype = 'b' order by typname"
  rs <- pg.spi.exec(sql)
  for(i in 1:nrow(rs))
  {
    typobj <- rs[i,1]
    typval <- rs[i,2]
    if (substr(typobj,1,1) == "_")
      typobj <- paste("ARRAYOF", substr(typobj,2,nchar(typobj)), sep="")
    assign(typobj, typval, .GlobalEnv)
  }
  return("OK")
$$
    LANGUAGE plr;


--
-- Name: locate_along_measure(geometry, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION locate_along_measure(geometry, double precision) RETURNS geometry
    AS $_$SELECT locate_between_measures($1, $2, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: locate_between_measures(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION locate_between_measures(geometry, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_locate_between_m'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: lwgeom_gist_compress(internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_compress(internal) RETURNS internal
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_compress'
    LANGUAGE c;


--
-- Name: lwgeom_gist_consistent(internal, geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_consistent(internal, geometry, integer) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_consistent'
    LANGUAGE c;


--
-- Name: lwgeom_gist_decompress(internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_decompress(internal) RETURNS internal
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_decompress'
    LANGUAGE c;


--
-- Name: lwgeom_gist_penalty(internal, internal, internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_penalty(internal, internal, internal) RETURNS internal
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_penalty'
    LANGUAGE c;


--
-- Name: lwgeom_gist_picksplit(internal, internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_picksplit(internal, internal) RETURNS internal
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_picksplit'
    LANGUAGE c;


--
-- Name: lwgeom_gist_same(box2d, box2d, internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_same(box2d, box2d, internal) RETURNS internal
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_same'
    LANGUAGE c;


--
-- Name: lwgeom_gist_union(bytea, internal); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION lwgeom_gist_union(bytea, internal) RETURNS internal
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_union'
    LANGUAGE c;


--
-- Name: m(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION m(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_m_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makebox2d(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makebox2d(geometry, geometry) RETURNS box2d
    AS '$libdir/liblwgeom.so.1.1', 'BOX2DFLOAT4_construct'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makebox3d(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makebox3d(geometry, geometry) RETURNS box3d
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_construct'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makeline(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makeline(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makeline'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makeline_garray(geometry[]); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makeline_garray(geometry[]) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makeline_garray'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makepoint(double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makepoint(double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makepoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makepoint(double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makepoint(double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makepoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makepoint(double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makepoint(double precision, double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makepoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makepointm(double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makepointm(double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makepoint3dm'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makepolygon(geometry, geometry[]); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makepolygon(geometry, geometry[]) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makepoly'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: makepolygon(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION makepolygon(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_makepoly'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: max_distance(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION max_distance(geometry, geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_maxdistance2d_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: mem_size(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mem_size(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_mem_size'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: mlinefromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mlinefromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE
	WHEN geometrytype(GeomFromText($1, $2)) = 'MULTILINESTRING'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mlinefromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mlinefromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'MULTILINESTRING'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mlinefromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mlinefromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mlinefromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mlinefromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpointfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpointfromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1,$2)) = 'MULTIPOINT'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpointfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpointfromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'MULTIPOINT'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpointfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpointfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1,$2)) = 'MULTIPOINT'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpointfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpointfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOINT'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpolyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpolyfromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'MULTIPOLYGON'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpolyfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpolyfromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'MULTIPOLYGON'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpolyfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpolyfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: mpolyfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION mpolyfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multi(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multi(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_force_multi'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: multilinefromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multilinefromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multilinefromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multilinefromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTILINESTRING'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multilinestringfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multilinestringfromtext(text) RETURNS geometry
    AS $_$SELECT MLineFromText($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multilinestringfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multilinestringfromtext(text, integer) RETURNS geometry
    AS $_$SELECT MLineFromText($1, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipointfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipointfromtext(text, integer) RETURNS geometry
    AS $_$SELECT MPointFromText($1, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipointfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipointfromtext(text) RETURNS geometry
    AS $_$SELECT MPointFromText($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipointfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipointfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1,$2)) = 'MULTIPOINT'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipointfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipointfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOINT'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipolyfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipolyfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipolyfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipolyfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'MULTIPOLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipolygonfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipolygonfromtext(text, integer) RETURNS geometry
    AS $_$SELECT MPolyFromText($1, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: multipolygonfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION multipolygonfromtext(text) RETURNS geometry
    AS $_$SELECT MPolyFromText($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: ndims(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION ndims(geometry) RETURNS smallint
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_ndims'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: noop(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION noop(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_noop'
    LANGUAGE c STRICT;


--
-- Name: npoints(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION npoints(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_npoints'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: nrings(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION nrings(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_nrings'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: numgeometries(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION numgeometries(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_numgeometries_collection'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: numinteriorrings(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION numinteriorrings(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_numinteriorrings_polygon'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: numpoints(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION numpoints(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_numpoints_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: overlaps(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION "overlaps"(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'overlaps'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: p_r_pdf_test(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION p_r_pdf_test() RETURNS text
    AS $$temp = c("hi", "hey there", "whatcha doin");pdf('/tmp/temp2.pdf');plot(rnorm(100), rnorm(100), main=temp[3]);dev.off();print('done');$$
    LANGUAGE plr;


--
-- Name: perimeter(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION perimeter(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_perimeter_poly'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: perimeter2d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION perimeter2d(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_perimeter2d_poly'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: perimeter3d(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION perimeter3d(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_perimeter_poly'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: pg_file_length(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pg_file_length(text) RETURNS bigint
    AS $_$SELECT len FROM pg_file_stat($1) AS s(len int8, c timestamp, a timestamp, m timestamp, i bool)$_$
    LANGUAGE sql STRICT;


--
-- Name: pg_file_rename(text, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pg_file_rename(text, text) RETURNS boolean
    AS $_$SELECT pg_file_rename($1, $2, NULL); $_$
    LANGUAGE sql STRICT;


--
-- Name: plr_array_accum(double precision[], double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plr_array_accum(double precision[], double precision) RETURNS double precision[]
    AS '$libdir/plr', 'plr_array_accum'
    LANGUAGE c;


--
-- Name: plr_array_push(double precision[], double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plr_array_push(double precision[], double precision) RETURNS double precision[]
    AS '$libdir/plr', 'plr_array_push'
    LANGUAGE c STRICT;


--
-- Name: plr_environ(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plr_environ() RETURNS SETOF plr_environ_type
    AS '$libdir/plr', 'plr_environ'
    LANGUAGE c;


--
-- Name: plr_singleton_array(double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION plr_singleton_array(double precision) RETURNS double precision[]
    AS '$libdir/plr', 'plr_array'
    LANGUAGE c STRICT;


--
-- Name: point_inside_circle(geometry, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION point_inside_circle(geometry, double precision, double precision, double precision) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_inside_circle_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: pointfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pointfromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'POINT'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: pointfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pointfromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'POINT'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: pointfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pointfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'POINT'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: pointfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pointfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'POINT'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: pointn(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pointn(geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_pointn_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: pointonsurface(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION pointonsurface(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'pointonsurface'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: polyfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polyfromtext(text) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1)) = 'POLYGON'
	THEN GeomFromText($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polyfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polyfromtext(text, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromText($1, $2)) = 'POLYGON'
	THEN GeomFromText($1,$2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polyfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polyfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1, $2)) = 'POLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polyfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polyfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'POLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polygonfromtext(text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polygonfromtext(text, integer) RETURNS geometry
    AS $_$SELECT PolyFromText($1, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polygonfromtext(text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polygonfromtext(text) RETURNS geometry
    AS $_$SELECT PolyFromText($1)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polygonfromwkb(bytea, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polygonfromwkb(bytea, integer) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1,$2)) = 'POLYGON'
	THEN GeomFromWKB($1, $2)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polygonfromwkb(bytea); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polygonfromwkb(bytea) RETURNS geometry
    AS $_$
	SELECT CASE WHEN geometrytype(GeomFromWKB($1)) = 'POLYGON'
	THEN GeomFromWKB($1)
	ELSE NULL END
	$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: polygonize_garray(geometry[]); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION polygonize_garray(geometry[]) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'polygonize_garray'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: postgis_full_version(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_full_version() RETURNS text
    AS $$
DECLARE
	libver text;
	projver text;
	geosver text;
	jtsver text;
	usestats bool;
	dbproc text;
	relproc text;
	fullver text;
BEGIN
	SELECT postgis_lib_version() INTO libver;
	SELECT postgis_proj_version() INTO projver;
	SELECT postgis_geos_version() INTO geosver;
	SELECT postgis_jts_version() INTO jtsver;
	SELECT postgis_uses_stats() INTO usestats;
	SELECT postgis_scripts_installed() INTO dbproc;
	SELECT postgis_scripts_released() INTO relproc;

	fullver = 'POSTGIS="' || libver || '"';

	IF  geosver IS NOT NULL THEN
		fullver = fullver || ' GEOS="' || geosver || '"';
	END IF;

	IF  jtsver IS NOT NULL THEN
		fullver = fullver || ' JTS="' || jtsver || '"';
	END IF;

	IF  projver IS NOT NULL THEN
		fullver = fullver || ' PROJ="' || projver || '"';
	END IF;

	IF usestats THEN
		fullver = fullver || ' USE_STATS';
	END IF;

	-- fullver = fullver || ' DBPROC="' || dbproc || '"';
	-- fullver = fullver || ' RELPROC="' || relproc || '"';

	IF dbproc != relproc THEN
		fullver = fullver || ' (procs ' || dbproc || ' upgrade)';
	END IF;

	RETURN fullver;
END
$$
    LANGUAGE plpgsql IMMUTABLE;


--
-- Name: postgis_geos_version(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_geos_version() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_geos_version'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_gist_joinsel(internal, oid, internal, smallint); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_gist_joinsel(internal, oid, internal, smallint) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_joinsel'
    LANGUAGE c;


--
-- Name: postgis_gist_sel(internal, oid, internal, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_gist_sel(internal, oid, internal, integer) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_gist_sel'
    LANGUAGE c;


--
-- Name: postgis_jts_version(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION postgis_jts_version() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_jts_version'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_lib_build_date(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_lib_build_date() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_lib_build_date'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_lib_version(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_lib_version() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_lib_version'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_proj_version(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_proj_version() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_proj_version'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_scripts_build_date(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_scripts_build_date() RETURNS text
    AS $$SELECT '2006-01-22 07:51:16'::text AS version$$
    LANGUAGE sql IMMUTABLE;


--
-- Name: postgis_scripts_installed(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_scripts_installed() RETURNS text
    AS $$SELECT '1.1.0'::text AS version$$
    LANGUAGE sql IMMUTABLE;


--
-- Name: postgis_scripts_released(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_scripts_released() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_scripts_released'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_uses_stats(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_uses_stats() RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'postgis_uses_stats'
    LANGUAGE c IMMUTABLE;


--
-- Name: postgis_version(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION postgis_version() RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'postgis_version'
    LANGUAGE c IMMUTABLE;


--
-- Name: probe_geometry_columns(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION probe_geometry_columns() RETURNS text
    AS $$
DECLARE
	inserted integer;
	oldcount integer;
	probed integer;
	stale integer;
BEGIN

	SELECT count(*) INTO oldcount FROM geometry_columns;

	SELECT count(*) INTO probed
		FROM pg_class c, pg_attribute a, pg_type t, 
			pg_namespace n,
			pg_constraint sridcheck, pg_constraint typecheck

		WHERE t.typname = 'geometry'
		AND a.atttypid = t.oid
		AND a.attrelid = c.oid
		AND c.relnamespace = n.oid
		AND sridcheck.connamespace = n.oid
		AND typecheck.connamespace = n.oid

		AND sridcheck.conrelid = c.oid
		AND sridcheck.consrc LIKE '(srid('||a.attname||') = %)'
		AND typecheck.conrelid = c.oid
		AND typecheck.consrc LIKE
	'((geometrytype('||a.attname||') = ''%''::text) OR (% IS NULL))'
		;

	INSERT INTO geometry_columns SELECT
		''::varchar as f_table_catalogue,
		n.nspname::varchar as f_table_schema,
		c.relname::varchar as f_table_name,
		a.attname::varchar as f_geometry_column,
		2 as coord_dimension,
		trim(both  ' =)' from substr(sridcheck.consrc,
			strpos(sridcheck.consrc, '=')))::integer as srid,
		trim(both ' =)''' from substr(typecheck.consrc, 
			strpos(typecheck.consrc, '='),
			strpos(typecheck.consrc, '::')-
			strpos(typecheck.consrc, '=')
			))::varchar as type

		FROM pg_class c, pg_attribute a, pg_type t, 
			pg_namespace n,
			pg_constraint sridcheck, pg_constraint typecheck
		WHERE t.typname = 'geometry'
		AND a.atttypid = t.oid
		AND a.attrelid = c.oid
		AND c.relnamespace = n.oid
		AND sridcheck.connamespace = n.oid
		AND typecheck.connamespace = n.oid
		AND sridcheck.conrelid = c.oid
		AND sridcheck.consrc LIKE '(srid('||a.attname||') = %)'
		AND typecheck.conrelid = c.oid
		AND typecheck.consrc LIKE
	'((geometrytype('||a.attname||') = ''%''::text) OR (% IS NULL))'

                AND NOT EXISTS (
                        SELECT oid FROM geometry_columns gc
                        WHERE c.relname::varchar = gc.f_table_name
                        AND n.nspname::varchar = gc.f_table_schema
                        AND a.attname::varchar = gc.f_geometry_column
                );

	GET DIAGNOSTICS inserted = ROW_COUNT;

	IF oldcount > probed THEN
		stale = oldcount-probed;
	ELSE
		stale = 0;
	END IF;

        RETURN 'probed:'||probed||
		' inserted:'||inserted||
		' conflicts:'||probed-inserted||
		' stale:'||stale;
END

$$
    LANGUAGE plpgsql;


--
-- Name: r_max(integer, integer); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION r_max(integer, integer) RETURNS integer
    AS $_$
    library(GenKern)
    #x <- c(2,4,6,8,10) # make up some x-y data
    x <- pg.spi.exec ('SELECT X(the_geom) FROM cases')
    y <- pg.spi.exec ('SELECT Y(the_geom) FROM cases')
    #y <- pg.spi.exec ('SELECT Y(cases.the_geom) FROM cases NATURAL JOIN case_types NATURAL JOIN case_type_attributes NATURAL JOIN attributes WHERE attributes.name=''Serogroup'' AND value=''B'' AND reportdate BETWEEN ''2006-06-01'' AND ''2006-07-01''')
    png('/tmp/temp2.png')
    op <- KernSur(x, y, xgridsize=50, ygridsize=50, correlation=0, xbandwidth=1, ybandwidth=1, range.x=c(6,15), range.y=c(47,54))
    image(op$xords, op$yords, op$zden, col=terrain.colors(1000), axes=TRUE)
    #plot(y)
    dev.off()
$_$
    LANGUAGE plr STRICT;


--
-- Name: r_typenames(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION r_typenames() RETURNS SETOF r_typename
    AS $$
  x <- ls(name = .GlobalEnv, pat = "OID")
  y <- vector()
  for (i in 1:length(x)) {y[i] <- eval(parse(text = x[i]))}
  data.frame(typename = x, typeoid = y)
$$
    LANGUAGE plr;


--
-- Name: relate(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION relate(geometry, geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'relate_full'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: relate(geometry, geometry, text); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION relate(geometry, geometry, text) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'relate_pattern'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: reload_plr_modules(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION reload_plr_modules() RETURNS text
    AS '$libdir/plr', 'reload_plr_modules'
    LANGUAGE c;


--
-- Name: removepoint(geometry, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION removepoint(geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_removepoint'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: rename_geometry_table_constraints(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION rename_geometry_table_constraints() RETURNS text
    AS $$
SELECT 'rename_geometry_table_constraint() is obsoleted'::text
$$
    LANGUAGE sql IMMUTABLE;


--
-- Name: reverse(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION reverse(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_reverse'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: scale(geometry, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION scale(geometry, double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_scale'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: scale(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION scale(geometry, double precision, double precision) RETURNS geometry
    AS $_$ SELECT scale($1, $2, $3, 1) $_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: scangis_sero_cby(); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION scangis_sero_cby() RETURNS text
    AS $$sqlC <- paste("SELECT count(*) FROM cases NATURAL JOIN case_type_attributes WHERE value='C'",sep="");sqlB <- paste("SELECT count(*) FROM cases NATURAL JOIN case_type_attributes WHERE value='B'",sep="");sqlY <- paste("SELECT count(*) FROM cases NATURAL JOIN case_type_attributes WHERE value='Y'",sep="");strC <- pg.spi.exec (sqlC);strB <- pg.spi.exec (sqlB);strY <- pg.spi.exec (sqlY);str <- c(range(strC,strB),range(strY)[1]);pdf('/tmp/graph1.pdf');protocolNames <- c("C","B","Y");barplot(str,names.arg=protocolNames);mtext("Total number of cases",side=3);title(main="Graph 1",xlab="organized by serogroup");dev.off();print ('DONE');$$
    LANGUAGE plr;


--
-- Name: segmentize(geometry, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION segmentize(geometry, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_segmentize2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: setfactor(chip, real); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION setfactor(chip, real) RETURNS chip
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_setFactor'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: setpoint(geometry, integer, geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION setpoint(geometry, integer, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_setpoint_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: setsrid(chip, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION setsrid(chip, integer) RETURNS chip
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_setSRID'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: setsrid(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION setsrid(geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_setSRID'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: shift_longitude(geometry); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION shift_longitude(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_longitude_shift'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: simplify(geometry, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION simplify(geometry, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_simplify2d'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: snaptogrid(geometry, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION snaptogrid(geometry, double precision, double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_snaptogrid'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: snaptogrid(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION snaptogrid(geometry, double precision, double precision) RETURNS geometry
    AS $_$SELECT SnapToGrid($1, 0, 0, $2, $3)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: snaptogrid(geometry, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION snaptogrid(geometry, double precision) RETURNS geometry
    AS $_$SELECT SnapToGrid($1, 0, 0, $2, $2)$_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: snaptogrid(geometry, geometry, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION snaptogrid(geometry, geometry, double precision, double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_snaptogrid_pointoff'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: srid(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION srid(chip) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_getSRID'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: srid(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION srid(geometry) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_getSRID'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: startpoint(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION startpoint(geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_startpoint_linestring'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: summary(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION summary(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_summary'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: symdifference(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION symdifference(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'symdifference'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: symmetricdifference(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION symmetricdifference(geometry, geometry) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'symdifference'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: test_spi_tup(text); Type: FUNCTION; Schema: public; Owner: mreinhardt
--

CREATE FUNCTION test_spi_tup(text) RETURNS SETOF record
    AS $$
  pg.spi.exec(arg1)
$$
    LANGUAGE plr;


--
-- Name: text(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION text(geometry) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_to_text'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: text(boolean); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION text(boolean) RETURNS text
    AS '$libdir/liblwgeom.so.1.1', 'BOOL_to_text'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: touches(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION touches(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'touches'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: transform(geometry, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION transform(geometry, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'transform'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: transform_geometry(geometry, text, text, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION transform_geometry(geometry, text, text, integer) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'transform_geom'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: translate(geometry, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION translate(geometry, double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_translate'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: translate(geometry, double precision, double precision); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION translate(geometry, double precision, double precision) RETURNS geometry
    AS $_$ SELECT translate($1, $2, $3, 0) $_$
    LANGUAGE sql IMMUTABLE STRICT;


--
-- Name: transscale(geometry, double precision, double precision, double precision, double precision); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION transscale(geometry, double precision, double precision, double precision, double precision) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_transscale'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: unite_garray(geometry[]); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION unite_garray(geometry[]) RETURNS geometry
    AS '$libdir/liblwgeom.so.1.1', 'unite_garray'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: update_geometry_stats(); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION update_geometry_stats() RETURNS text
    AS $$ SELECT 'update_geometry_stats() has been obsoleted. Statistics are automatically built running the ANALYZE command'::text$$
    LANGUAGE sql;


--
-- Name: update_geometry_stats(character varying, character varying); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION update_geometry_stats(character varying, character varying) RETURNS text
    AS $$SELECT update_geometry_stats();$$
    LANGUAGE sql;


--
-- Name: updategeometrysrid(character varying, character varying, character varying, character varying, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION updategeometrysrid(character varying, character varying, character varying, character varying, integer) RETURNS text
    AS $_$
DECLARE
	catalog_name alias for $1; 
	schema_name alias for $2;
	table_name alias for $3;
	column_name alias for $4;
	new_srid alias for $5;
	myrec RECORD;
	okay boolean;
	cname varchar;
	real_schema name;

BEGIN


	-- Find, check or fix schema_name
	IF ( schema_name != '' ) THEN
		okay = 'f';

		FOR myrec IN SELECT nspname FROM pg_namespace WHERE text(nspname) = schema_name LOOP
			okay := 't';
		END LOOP;

		IF ( okay <> 't' ) THEN
			RAISE EXCEPTION 'Invalid schema name';
		ELSE
			real_schema = schema_name;
		END IF;
	ELSE
		SELECT INTO real_schema current_schema()::text;
	END IF;

 	-- Find out if the column is in the geometry_columns table
	okay = 'f';
	FOR myrec IN SELECT * from geometry_columns where f_table_schema = text(real_schema) and f_table_name = table_name and f_geometry_column = column_name LOOP
		okay := 't';
	END LOOP; 
	IF (okay <> 't') THEN 
		RAISE EXCEPTION 'column not found in geometry_columns table';
		RETURN 'f';
	END IF;

	-- Update ref from geometry_columns table
	EXECUTE 'UPDATE geometry_columns SET SRID = ' || new_srid || 
		' where f_table_schema = ' ||
		quote_literal(real_schema) || ' and f_table_name = ' ||
		quote_literal(table_name)  || ' and f_geometry_column = ' ||
		quote_literal(column_name);
	
	-- Make up constraint name
	cname = 'enforce_srid_'  || column_name;

	-- Drop enforce_srid constraint
	EXECUTE 'ALTER TABLE ' || quote_ident(real_schema) ||
		'.' || quote_ident(table_name) ||
		' DROP constraint ' || quote_ident(cname);

	-- Update geometries SRID
	EXECUTE 'UPDATE ' || quote_ident(real_schema) ||
		'.' || quote_ident(table_name) ||
		' SET ' || quote_ident(column_name) ||
		' = setSRID(' || quote_ident(column_name) ||
		', ' || new_srid || ')';

	-- Reset enforce_srid constraint
	EXECUTE 'ALTER TABLE ' || quote_ident(real_schema) ||
		'.' || quote_ident(table_name) ||
		' ADD constraint ' || quote_ident(cname) ||
		' CHECK (srid(' || quote_ident(column_name) ||
		') = ' || new_srid || ')';

	RETURN real_schema || '.' || table_name || '.' || column_name ||' SRID changed to ' || new_srid;
	
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: updategeometrysrid(character varying, character varying, character varying, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION updategeometrysrid(character varying, character varying, character varying, integer) RETURNS text
    AS $_$
DECLARE
	ret  text;
BEGIN
	SELECT UpdateGeometrySRID('',$1,$2,$3,$4) into ret;
	RETURN ret;
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: updategeometrysrid(character varying, character varying, integer); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION updategeometrysrid(character varying, character varying, integer) RETURNS text
    AS $_$
DECLARE
	ret  text;
BEGIN
	SELECT UpdateGeometrySRID('','',$1,$2,$3) into ret;
	RETURN ret;
END;
$_$
    LANGUAGE plpgsql STRICT;


--
-- Name: width(chip); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION width(chip) RETURNS integer
    AS '$libdir/liblwgeom.so.1.1', 'CHIP_getWidth'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: within(geometry, geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION within(geometry, geometry) RETURNS boolean
    AS '$libdir/liblwgeom.so.1.1', 'within'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: x(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION x(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_x_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: xmax(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION xmax(box3d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_xmax'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: xmin(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION xmin(box3d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_xmin'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: y(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION y(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_y_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: ymax(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION ymax(box3d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_ymax'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: ymin(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION ymin(box3d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_ymin'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: z(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION z(geometry) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_z_point'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: zmax(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION zmax(box3d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_zmax'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: zmflag(geometry); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION zmflag(geometry) RETURNS smallint
    AS '$libdir/liblwgeom.so.1.1', 'LWGEOM_zmflag'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: zmin(box3d); Type: FUNCTION; Schema: public; Owner: admin
--

CREATE FUNCTION zmin(box3d) RETURNS double precision
    AS '$libdir/liblwgeom.so.1.1', 'BOX3D_zmin'
    LANGUAGE c IMMUTABLE STRICT;


--
-- Name: accum(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE accum (
    BASETYPE = geometry,
    SFUNC = geom_accum,
    STYPE = geometry[]
);


--
-- Name: collect(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE collect (
    BASETYPE = geometry,
    SFUNC = geom_accum,
    STYPE = geometry[],
    FINALFUNC = collect_garray
);


--
-- Name: extent(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE extent (
    BASETYPE = geometry,
    SFUNC = public.combine_bbox,
    STYPE = box2d
);


--
-- Name: extent3d(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE extent3d (
    BASETYPE = geometry,
    SFUNC = public.combine_bbox,
    STYPE = box3d
);


--
-- Name: geomunion(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE geomunion (
    BASETYPE = geometry,
    SFUNC = geom_accum,
    STYPE = geometry[],
    FINALFUNC = unite_garray
);


--
-- Name: makeline(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE makeline (
    BASETYPE = geometry,
    SFUNC = geom_accum,
    STYPE = geometry[],
    FINALFUNC = makeline_garray
);


--
-- Name: memcollect(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE memcollect (
    BASETYPE = geometry,
    SFUNC = public.collect,
    STYPE = geometry
);


--
-- Name: memgeomunion(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE memgeomunion (
    BASETYPE = geometry,
    SFUNC = public.geomunion,
    STYPE = geometry
);


--
-- Name: polygonize(geometry); Type: AGGREGATE; Schema: public; Owner: admin
--

CREATE AGGREGATE polygonize (
    BASETYPE = geometry,
    SFUNC = geom_accum,
    STYPE = geometry[],
    FINALFUNC = polygonize_garray
);


--
-- Name: &&; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR && (
    PROCEDURE = geometry_overlap,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = &&,
    RESTRICT = postgis_gist_sel,
    JOIN = postgis_gist_joinsel
);


--
-- Name: &<; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR &< (
    PROCEDURE = geometry_overleft,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = &>,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: &<|; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR &<| (
    PROCEDURE = geometry_overbelow,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = |&>,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: &>; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR &> (
    PROCEDURE = geometry_overright,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = &<,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: <; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR < (
    PROCEDURE = geometry_lt,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = >,
    NEGATOR = >=,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: <<; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR << (
    PROCEDURE = geometry_left,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = >>,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: <<|; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR <<| (
    PROCEDURE = geometry_below,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = |>>,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: <=; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR <= (
    PROCEDURE = geometry_le,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = >=,
    NEGATOR = >,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: =; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR = (
    PROCEDURE = geometry_eq,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = =,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: >; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR > (
    PROCEDURE = geometry_gt,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = <,
    NEGATOR = <=,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: >=; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR >= (
    PROCEDURE = geometry_ge,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = <=,
    NEGATOR = <,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: >>; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR >> (
    PROCEDURE = geometry_right,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = <<,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: @; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR @ (
    PROCEDURE = geometry_contained,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = ~,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: |&>; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR |&> (
    PROCEDURE = geometry_overabove,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = &<|,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: |>>; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR |>> (
    PROCEDURE = geometry_above,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = <<|,
    RESTRICT = positionsel,
    JOIN = positionjoinsel
);


--
-- Name: ~; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR ~ (
    PROCEDURE = geometry_contain,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = @,
    RESTRICT = contsel,
    JOIN = contjoinsel
);


--
-- Name: ~=; Type: OPERATOR; Schema: public; Owner: admin
--

CREATE OPERATOR ~= (
    PROCEDURE = geometry_same,
    LEFTARG = geometry,
    RIGHTARG = geometry,
    COMMUTATOR = ~=,
    RESTRICT = eqsel,
    JOIN = eqjoinsel
);


--
-- Name: btree_geometry_ops; Type: OPERATOR CLASS; Schema: public; Owner: admin
--

CREATE OPERATOR CLASS btree_geometry_ops
    DEFAULT FOR TYPE geometry USING btree AS
    OPERATOR 1 <(geometry,geometry) ,
    OPERATOR 2 <=(geometry,geometry) ,
    OPERATOR 3 =(geometry,geometry) ,
    OPERATOR 4 >=(geometry,geometry) ,
    OPERATOR 5 >(geometry,geometry) ,
    FUNCTION 1 geometry_cmp(geometry,geometry);


--
-- Name: gist_geometry_ops; Type: OPERATOR CLASS; Schema: public; Owner: admin
--

CREATE OPERATOR CLASS gist_geometry_ops
    DEFAULT FOR TYPE geometry USING gist AS
    STORAGE box2d ,
    OPERATOR 1 <<(geometry,geometry) RECHECK ,
    OPERATOR 2 &<(geometry,geometry) RECHECK ,
    OPERATOR 3 &&(geometry,geometry) RECHECK ,
    OPERATOR 4 &>(geometry,geometry) RECHECK ,
    OPERATOR 5 >>(geometry,geometry) RECHECK ,
    OPERATOR 6 ~=(geometry,geometry) RECHECK ,
    OPERATOR 7 ~(geometry,geometry) RECHECK ,
    OPERATOR 8 @(geometry,geometry) RECHECK ,
    OPERATOR 9 &<|(geometry,geometry) RECHECK ,
    OPERATOR 10 <<|(geometry,geometry) RECHECK ,
    OPERATOR 11 |>>(geometry,geometry) RECHECK ,
    OPERATOR 12 |&>(geometry,geometry) RECHECK ,
    FUNCTION 1 lwgeom_gist_consistent(internal,geometry,integer) ,
    FUNCTION 2 lwgeom_gist_union(bytea,internal) ,
    FUNCTION 3 lwgeom_gist_compress(internal) ,
    FUNCTION 4 lwgeom_gist_decompress(internal) ,
    FUNCTION 5 lwgeom_gist_penalty(internal,internal,internal) ,
    FUNCTION 6 lwgeom_gist_picksplit(internal,internal) ,
    FUNCTION 7 lwgeom_gist_same(box2d,box2d,internal);


SET search_path = pg_catalog;

--
-- Name: CAST (boolean AS text); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (boolean AS text) WITH FUNCTION public.text(boolean) AS IMPLICIT;


--
-- Name: CAST (public.box2d AS public.box3d); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.box2d AS public.box3d) WITH FUNCTION public.box3d(public.box2d) AS IMPLICIT;


--
-- Name: CAST (public.box2d AS public.geometry); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.box2d AS public.geometry) WITH FUNCTION public.geometry(public.box2d) AS IMPLICIT;


--
-- Name: CAST (public.box3d AS box); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.box3d AS box) WITH FUNCTION public.box(public.box3d) AS IMPLICIT;


--
-- Name: CAST (public.box3d AS public.box2d); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.box3d AS public.box2d) WITH FUNCTION public.box2d(public.box3d) AS IMPLICIT;


--
-- Name: CAST (public.box3d AS public.geometry); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.box3d AS public.geometry) WITH FUNCTION public.geometry(public.box3d) AS IMPLICIT;


--
-- Name: CAST (bytea AS public.geometry); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (bytea AS public.geometry) WITH FUNCTION public.geometry(bytea) AS IMPLICIT;


--
-- Name: CAST (public.chip AS public.geometry); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.chip AS public.geometry) WITH FUNCTION public.geometry(public.chip) AS IMPLICIT;


--
-- Name: CAST (public.geometry AS box); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.geometry AS box) WITH FUNCTION public.box(public.geometry) AS IMPLICIT;


--
-- Name: CAST (public.geometry AS public.box2d); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.geometry AS public.box2d) WITH FUNCTION public.box2d(public.geometry) AS IMPLICIT;


--
-- Name: CAST (public.geometry AS public.box3d); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.geometry AS public.box3d) WITH FUNCTION public.box3d(public.geometry) AS IMPLICIT;


--
-- Name: CAST (public.geometry AS bytea); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.geometry AS bytea) WITH FUNCTION public.bytea(public.geometry) AS IMPLICIT;


--
-- Name: CAST (public.geometry AS text); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (public.geometry AS text) WITH FUNCTION public.text(public.geometry) AS IMPLICIT;


--
-- Name: CAST (text AS public.geometry); Type: CAST; Schema: pg_catalog; Owner: 
--

CREATE CAST (text AS public.geometry) WITH FUNCTION public.geometry(text) AS IMPLICIT;


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = true;

--
-- Name: attributes; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE attributes (
    attribute_id serial NOT NULL,
    name character varying NOT NULL,
    "group" character varying
);


--
-- Name: case_type_attributes; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE case_type_attributes (
    case_type_attribute_id serial NOT NULL,
    case_type_id integer NOT NULL,
    attribute_id integer NOT NULL,
    value character varying NOT NULL
);


--
-- Name: all_serogroups; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW all_serogroups AS
    SELECT case_type_attributes.value FROM (case_type_attributes NATURAL JOIN attributes) WHERE ((attributes."group")::text ~~ 'Serogroups'::text) GROUP BY case_type_attributes.value;


--
-- Name: area_populations; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE area_populations (
    area_id integer NOT NULL,
    "year" smallint NOT NULL,
    population bigint NOT NULL
);


--
-- Name: area_types; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE area_types (
    area_type_id serial NOT NULL,
    description character varying NOT NULL,
    country character varying NOT NULL,
    tier smallint NOT NULL,
    parent integer,
    srid integer DEFAULT 4326,
    minx double precision DEFAULT 0,
    miny double precision DEFAULT 0,
    maxx double precision DEFAULT 0,
    maxy double precision DEFAULT 0,
    active boolean DEFAULT false,
    "group" boolean DEFAULT false
);


--
-- Name: areas; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE areas (
    area_id serial NOT NULL,
    identifier character varying,
    the_geom geometry NOT NULL,
    numeric_identifier integer,
    area_type_id integer,
    population bigint,
    CONSTRAINT enforce_geotype_the_geom CHECK ((((geometrytype(the_geom) = 'MULTIPOLYGON'::text) OR (geometrytype(the_geom) = 'POLYGON'::text)) OR (the_geom IS NULL))),
    CONSTRAINT enforce_srid_the_geom CHECK ((srid(the_geom) = 4326))
);


--
-- Name: area_with_max_bordercount; Type: VIEW; Schema: public; Owner: mreinhardt
--

CREATE VIEW area_with_max_bordercount AS
    SELECT count(touches(areas.the_geom, toucher.env)) AS count, toucher.identifier FROM areas, (SELECT areas.identifier, areas.the_geom AS env FROM areas) toucher WHERE (touches(areas.the_geom, toucher.env) = true) GROUP BY toucher.identifier ORDER BY count(touches(areas.the_geom, toucher.env)) DESC LIMIT 1;


SET default_with_oids = false;

--
-- Name: case_types; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE case_types (
    case_type_id serial NOT NULL,
    identifier character varying NOT NULL,
    since timestamp without time zone DEFAULT '2005-11-17 10:11:57.166821'::timestamp without time zone
);


--
-- Name: cases; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE cases (
    case_id integer NOT NULL,
    age smallint,
    gender character(1),
    reportdate date,
    incidencedate date,
    case_type_id serial NOT NULL,
    the_geom geometry,
    last_change timestamp without time zone DEFAULT ('now'::text)::timestamp(6) with time zone,
    CONSTRAINT enforce_geotype_place_geom CHECK (((geometrytype(the_geom) = 'POINT'::text) OR (the_geom IS NULL))),
    CONSTRAINT enforce_srid_place_geom CHECK ((srid(the_geom) = 4326)),
    CONSTRAINT reported_cases_gender_check CHECK ((((gender = 'm'::bpchar) OR (gender = 'f'::bpchar)) OR (gender = '?'::bpchar)))
);


SET default_with_oids = true;

--
-- Name: contains_area_case; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contains_area_case (
    area_id integer NOT NULL,
    case_id integer NOT NULL
);


--
-- Name: types_with_attributes; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW types_with_attributes AS
    SELECT types.case_type_id, types.identifier, ta.value AS attvalue, a.name AS attname, a."group" AS attgroup FROM ((case_types types NATURAL JOIN case_type_attributes ta) NATURAL JOIN attributes a);


--
-- Name: areas_with_cases; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW areas_with_cases AS
    SELECT areas.oid, areas.the_geom, areas.population, types_with_attributes.attname, types_with_attributes.attvalue, areas.area_type_id, area_types.parent, to_char((cases.reportdate)::timestamp with time zone, 'YYYY'::text) AS inciyear FROM contains_area_case, areas, cases WHERE ((contains_area_case.area_id = areas.area_id) AND (contains_area_case.case_id = cases.case_id));


--
-- Name: areas_with_types; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW areas_with_types AS
    SELECT areas.area_type_id, areas.area_id, areas.identifier, areas.the_geom, areas.numeric_identifier, areas.population, area_types.description, area_types.country, area_types.tier, area_types.parent, area_types.srid, area_types.minx, area_types.miny, area_types.maxx, area_types.maxy FROM (areas NATURAL JOIN area_types);


--
-- Name: bounding_box; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW bounding_box AS
    SELECT astext(envelope(transform(geomunion(areas.the_geom), 31467))) AS astext FROM areas WHERE (areas.area_type_id = 4);


--
-- Name: buffer04; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW buffer04 AS
    SELECT cases.case_id AS oid, setsrid(buffer(cases.the_geom, (0.2)::double precision), 4326) AS the_geom FROM cases WHERE (to_char((cases.reportdate)::timestamp with time zone, 'YYYY'::text) = (2004)::text);


--
-- Name: buffer04_2; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW buffer04_2 AS
    SELECT cases.case_id AS oid, cases.the_geom FROM cases WHERE (to_char((cases.reportdate)::timestamp with time zone, 'YYYY'::text) = (2004)::text);


--
-- Name: case_types_nrzm; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW case_types_nrzm AS
    SELECT case_types.case_type_id, ((((((split_part((case_types.identifier)::text, ';'::text, 1) || ' : P1.'::text) || split_part((case_types.identifier)::text, ';'::text, 2)) || ','::text) || split_part((case_types.identifier)::text, ';'::text, 3)) || ' : F'::text) || split_part((case_types.identifier)::text, ';'::text, 4)) AS nice_identifier, case_types.identifier FROM case_types;


--
-- Name: case_types_with_count; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW case_types_with_count AS
    SELECT case_types.case_type_id, case_types.identifier, count(*) AS count FROM (cases NATURAL JOIN case_types) GROUP BY case_types.case_type_id, case_types.identifier ORDER BY count(*) DESC;


--
-- Name: cases_random; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW cases_random AS
    SELECT setsrid(translate(cases.the_geom, (random() / (30)::double precision), (random() / (30)::double precision), (0)::double precision), 4326) AS the_geom, cases.case_id, cases.age, cases.gender, cases.reportdate, cases.incidencedate, cases.case_type_id FROM cases;


--
-- Name: cities; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE cities (
    gid serial NOT NULL,
    __gid bigint,
    full_name character varying,
    dd_long numeric,
    dd_lat numeric,
    pc character varying,
    fc character varying,
    rc bigint,
    ufi bigint,
    uni bigint,
    utm character varying,
    dsg character varying,
    cc1 character varying,
    adm1 bigint,
    nt character varying,
    pc_orig bigint,
    homepage character varying,
    bundesland character varying,
    population character varying,
    the_geom geometry,
    population_n bigint,
    CONSTRAINT enforce_dims_the_geom CHECK ((ndims(the_geom) = 2)),
    CONSTRAINT enforce_geotype_the_geom CHECK (((geometrytype(the_geom) = 'POINT'::text) OR (the_geom IS NULL))),
    CONSTRAINT enforce_srid_the_geom CHECK ((srid(the_geom) = 4326))
);


SET default_with_oids = false;

--
-- Name: satscan_cluster_cases; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE satscan_cluster_cases (
    satscan_cluster_id integer NOT NULL,
    case_id integer NOT NULL,
    satscan_cluster_case_id integer DEFAULT nextval('public.satscan_cluster_cases_satscan_cluster_case_id_seq'::text) NOT NULL
);


--
-- Name: satscan_clusters; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE satscan_clusters (
    satscan_cluster_id serial NOT NULL,
    area_id integer,
    number integer NOT NULL,
    satscan_job_id integer NOT NULL,
    expectedcases real,
    circleradius real,
    llr double precision,
    pvalue double precision,
    analysisdate date NOT NULL,
    startdate date,
    enddate date,
    case_type_id integer NOT NULL
);


--
-- Name: cluster_cases; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW cluster_cases AS
    SELECT cases.case_id AS pnr, cases.reportdate, satscan_clusters.satscan_cluster_id, satscan_clusters.number, satscan_clusters.expectedcases, satscan_clusters.circleradius, satscan_clusters.llr, satscan_clusters.pvalue, satscan_clusters.analysisdate, satscan_clusters.startdate, satscan_clusters.enddate, case_types.identifier AS finetype, areas.identifier AS locname, satscan_clusters.satscan_job_id FROM ((((satscan_clusters NATURAL JOIN areas) NATURAL JOIN satscan_cluster_cases) JOIN cases ON ((cases.case_id = satscan_cluster_cases.case_id))) JOIN case_types ON ((cases.case_type_id = case_types.case_type_id))) WHERE (satscan_clusters.pvalue <= (0.05)::double precision);


--
-- Name: cluster_feedback; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE cluster_feedback (
    work_contact character varying,
    school_contact character varying,
    relationship character varying,
    relationship_by_marriage character varying,
    leisure_contact character varying,
    misc_contact character varying,
    mass_event character varying,
    disco_pub character varying,
    known_acquaintance_case character varying,
    info_by_report boolean,
    satscan_cluster_id integer,
    cluster_feedback_id serial NOT NULL,
    tan character varying NOT NULL,
    case_id integer NOT NULL,
    last_change timestamp without time zone,
    expiration_date date
);


--
-- Name: cluster_report; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW cluster_report AS
    SELECT cluster_cases.pnr, cluster_cases.reportdate, cluster_cases.satscan_cluster_id, cluster_cases.number, cluster_cases.expectedcases, cluster_cases.circleradius, cluster_cases.llr, cluster_cases.pvalue, cluster_cases.analysisdate, cluster_cases.startdate, cluster_cases.enddate, cluster_cases.finetype, cluster_cases.locname, cluster_cases.satscan_job_id FROM cluster_cases WHERE (((cluster_cases.satscan_job_id = 211) OR (cluster_cases.satscan_job_id = 110)) AND (cluster_cases.analysisdate = '2005-10-01'::date)) ORDER BY cluster_cases.satscan_cluster_id;


--
-- Name: count_cases_in_perimeter; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW count_cases_in_perimeter AS
    SELECT c1.case_id, buffer(c1.the_geom, (0.1)::double precision) AS the_geom, count(*) AS count FROM cases c1, cases c2 WHERE ((buffer(c1.the_geom, (0.1)::double precision, 1) && c2.the_geom) AND (distance(c1.the_geom, c2.the_geom) < (0.1)::double precision)) GROUP BY c1.case_id, c1.the_geom;


--
-- Name: count_cases_perimeter; Type: VIEW; Schema: public; Owner: admin
--

CREATE VIEW count_cases_perimeter AS
    SELECT c1.case_id, transform(setsrid(buffer(transform(centroid(c1.the_geom), 31467), (100000)::double precision), 31467), 4326) AS the_geom, (count(*) * 2) AS count FROM cases c1, cases c2 WHERE ((buffer(c1.the_geom, (0.1)::double precision, 1) && c2.the_geom) AND (distance(transform(c1.the_geom, 31467), transform(c2.the_geom, 31467)) <= (1000000)::double precision)) GROUP BY c1.case_id, c1.the_geom;


SET default_with_oids = true;

--
-- Name: geometry_columns; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE geometry_columns (
    f_table_catalog character varying(256) NOT NULL,
    f_table_schema character varying(256) NOT NULL,
    f_table_name character varying(256) NOT NULL,
    f_geometry_column character varying(256) NOT NULL,
    coord_dimension integer NOT NULL,
    srid integer NOT NULL,
    "type" character varying(30) NOT NULL
);


--
-- Name: gn; Type: TABLE; Schema: public; Owner: mreinhardt; Tablespace: 
--

CREATE TABLE gn (
    gid serial NOT NULL,
    area numeric,
    perimeter numeric,
    gn250_ bigint,
    gn250_id bigint,
    staat character varying(3),
    ags integer,
    name character varying(38),
    genus character varying(2),
    syno character varying(38),
    genussyno character varying(2),
    amt character varying(38),
    hoehe smallint,
    groesse smallint,
    ewz integer,
    gewkgk smallint,
    natrhe smallint,
    oskaa character varying(51),
    oska1 smallint,
    oska2 smallint,
    oska3 smallint,
    oska4 smallint,
    oska5 smallint,
    oska6 smallint,
    oska7 smallint,
    oska8 smallint,
    oska9 smallint,
    oska10 smallint,
    geola integer,
    geobr integer,
    gkre integer,
    gkho integer,
    utmea integer,
    utmno integer,
    tk25 character varying(6),
    tk50 character varying(6),
    tk100 character varying(6),
    tk200 character varying(6),
    jog250 smallint,
    tk500 smallint,
    tk1000 smallint,
    the_geom geometry,
    CONSTRAINT enforce_dims_the_geom CHECK ((ndims(the_geom) = 2)),
    CONSTRAINT enforce_geotype_the_geom CHECK (((geometrytype(the_geom) = 'POINT'::text) OR (the_geom IS NULL))),
    CONSTRAINT enforce_srid_the_geom CHECK ((srid(the_geom) = 4326))
);


--
-- Name: krs; Type: TABLE; Schema: public; Owner: mreinhardt; Tablespace: 
--

CREATE TABLE krs (
    gid serial NOT NULL,
    use integer,
    rs character varying(12),
    rau_rs character varying(12),
    rau_shn character varying(12),
    gen character varying(50),
    des character varying(50),
    ags character varying(8),
    shn character varying(13),
    shn_alt character varying(12),
    ewz integer,
    wirksamkei character varying(8),
    the_geom geometry,
    CONSTRAINT enforce_dims_the_geom CHECK ((ndims(the_geom) = 2)),
    CONSTRAINT enforce_geotype_the_geom CHECK (((geometrytype(the_geom) = 'MULTIPOLYGON'::text) OR (the_geom IS NULL))),
    CONSTRAINT enforce_srid_the_geom CHECK ((srid(the_geom) = 4326))
);


SET default_with_oids = false;

--
-- Name: nrzm_imprt; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE nrzm_imprt (
    pnr integer NOT NULL,
    eingang character varying(16),
    entnahme character varying(16),
    sg character varying(16),
    vr1 character varying(16),
    vr2 character varying(16),
    feta character varying(16),
    altr character varying,
    lat character varying,
    lon character varying
);


SET default_with_oids = true;

--
-- Name: plot_test; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE plot_test (
    plot_name character varying NOT NULL,
    blob oid
);


--
-- Name: plr_modules; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE plr_modules (
    modseq integer,
    modsrc text
);


--
-- Name: satscan_cluster_cases_satscan_cluster_case_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE satscan_cluster_cases_satscan_cluster_case_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- Name: satscan_executions; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE satscan_executions (
    satscan_job_id integer NOT NULL,
    case_type_id integer NOT NULL,
    planned_execution date NOT NULL
);


--
-- Name: satscan_jobs; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE satscan_jobs (
    satscan_job_id serial NOT NULL,
    name character varying NOT NULL,
    lastrun date,
    analysistype integer NOT NULL,
    modeltype integer DEFAULT 0 NOT NULL,
    scanareas integer NOT NULL,
    montecarloreps integer DEFAULT 999 NOT NULL,
    timeaggregationunits integer DEFAULT 0 NOT NULL,
    timeaggregationlength integer DEFAULT 1 NOT NULL,
    job_type integer NOT NULL,
    maxtemporalsize real,
    active boolean DEFAULT false NOT NULL,
    CONSTRAINT analysistype_check CHECK (((((((analysistype = 1) OR (analysistype = 2)) OR (analysistype = 3)) OR (analysistype = 4)) OR (analysistype = 5)) OR (analysistype = 6))),
    CONSTRAINT modeltype_check CHECK ((((modeltype = 0) OR (modeltype = 1)) OR (modeltype = 2))),
    CONSTRAINT montecarloreps_check CHECK (((((montecarloreps = 0) OR (montecarloreps = 9)) OR (montecarloreps = 999)) OR (((montecarloreps)::character varying)::text ~~ '%999'::text))),
    CONSTRAINT scanareas_check CHECK ((((scanareas = 1) OR (scanareas = 2)) OR (scanareas = 3))),
    CONSTRAINT timeaggregationlength_check CHECK ((timeaggregationlength > 0)),
    CONSTRAINT timeaggregationunits_check CHECK (((((timeaggregationunits = 0) OR (timeaggregationunits = 1)) OR (timeaggregationunits = 2)) OR (timeaggregationunits = 3)))
);


--
-- Name: spatial_ref_sys; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE spatial_ref_sys (
    srid integer NOT NULL,
    auth_name character varying(256),
    auth_srid integer,
    srtext character varying(2048),
    proj4text character varying(2048)
);


SET default_with_oids = false;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE user_roles (
    username character varying NOT NULL,
    rolename character varying NOT NULL,
    last_change timestamp without time zone DEFAULT ('now'::text)::timestamp(6) with time zone
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: admin; Tablespace: 
--

CREATE TABLE users (
    username character varying NOT NULL,
    "password" character varying NOT NULL,
    forename character varying NOT NULL,
    email character varying NOT NULL,
    phone character varying NOT NULL,
    organisation character varying,
    message text,
    registrationdate date DEFAULT ('now'::text)::date,
    lastlogin date,
    title character varying,
    lastname character varying NOT NULL,
    department character varying,
    "domain" character varying,
    street character varying,
    zip character varying,
    city character varying
);


--
-- Name: Tablewide unique attribute name; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY attributes
    ADD CONSTRAINT "Tablewide unique attribute name" UNIQUE (name);


--
-- Name: area_populations_area_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY area_populations
    ADD CONSTRAINT area_populations_area_id_key UNIQUE (area_id, "year");


--
-- Name: attributes_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY attributes
    ADD CONSTRAINT attributes_pkey PRIMARY KEY (attribute_id);


--
-- Name: cities_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY cities
    ADD CONSTRAINT cities_pkey PRIMARY KEY (gid);


--
-- Name: cluster_feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY cluster_feedback
    ADD CONSTRAINT cluster_feedback_pkey PRIMARY KEY (cluster_feedback_id);


--
-- Name: cluster_feedback_tan_key; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY cluster_feedback
    ADD CONSTRAINT cluster_feedback_tan_key UNIQUE (tan);


--
-- Name: contains_area_case_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contains_area_case
    ADD CONSTRAINT contains_area_case_pkey PRIMARY KEY (area_id, case_id);


--
-- Name: geometry_columns_pk; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY geometry_columns
    ADD CONSTRAINT geometry_columns_pk PRIMARY KEY (f_table_catalog, f_table_schema, f_table_name, f_geometry_column);


--
-- Name: gn_pkey; Type: CONSTRAINT; Schema: public; Owner: mreinhardt; Tablespace: 
--

ALTER TABLE ONLY gn
    ADD CONSTRAINT gn_pkey PRIMARY KEY (gid);


--
-- Name: krs_pkey; Type: CONSTRAINT; Schema: public; Owner: mreinhardt; Tablespace: 
--

ALTER TABLE ONLY krs
    ADD CONSTRAINT krs_pkey PRIMARY KEY (gid);


--
-- Name: location_types_id_key; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY area_types
    ADD CONSTRAINT location_types_id_key UNIQUE (area_type_id);


--
-- Name: location_types_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY area_types
    ADD CONSTRAINT location_types_pkey PRIMARY KEY (area_type_id);


--
-- Name: nrzm_imprt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY nrzm_imprt
    ADD CONSTRAINT nrzm_imprt_pkey PRIMARY KEY (pnr);


--
-- Name: plot_test_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY plot_test
    ADD CONSTRAINT plot_test_pkey PRIMARY KEY (plot_name);


--
-- Name: reported_cases_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY cases
    ADD CONSTRAINT reported_cases_pkey PRIMARY KEY (case_id);


--
-- Name: satscan_cluster_case_id; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY satscan_cluster_cases
    ADD CONSTRAINT satscan_cluster_case_id UNIQUE (satscan_cluster_case_id);


--
-- Name: satscan_cluster_cases_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY satscan_cluster_cases
    ADD CONSTRAINT satscan_cluster_cases_pkey PRIMARY KEY (satscan_cluster_id, case_id);


--
-- Name: satscan_clusters_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY satscan_clusters
    ADD CONSTRAINT satscan_clusters_pkey PRIMARY KEY (number, satscan_job_id, case_type_id, analysisdate);


--
-- Name: satscan_executions_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY satscan_executions
    ADD CONSTRAINT satscan_executions_pkey PRIMARY KEY (satscan_job_id, case_type_id, planned_execution);


--
-- Name: satscan_jobs_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY satscan_jobs
    ADD CONSTRAINT satscan_jobs_pkey PRIMARY KEY (name);


--
-- Name: spatial_ref_sys_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY spatial_ref_sys
    ADD CONSTRAINT spatial_ref_sys_pkey PRIMARY KEY (srid);


--
-- Name: testlocations_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY areas
    ADD CONSTRAINT testlocations_pkey PRIMARY KEY (area_id);


--
-- Name: types_agent_type_key; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY case_types
    ADD CONSTRAINT types_agent_type_key UNIQUE (identifier);


--
-- Name: types_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY case_types
    ADD CONSTRAINT types_pkey PRIMARY KEY (case_type_id);


--
-- Name: user; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "user" PRIMARY KEY (username);


--
-- Name: user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: admin; Tablespace: 
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (username, rolename);


--
-- Name: areas_gist_idx; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX areas_gist_idx ON areas USING gist (the_geom);

ALTER TABLE areas CLUSTER ON areas_gist_idx;


--
-- Name: cases_gist_idx; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX cases_gist_idx ON cases USING gist (the_geom);


--
-- Name: fki_; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX fki_ ON areas USING btree (area_type_id);


--
-- Name: fki_satscan_cluster_case_id; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX fki_satscan_cluster_case_id ON cluster_feedback USING btree (satscan_cluster_id);


--
-- Name: locations_gist_idx; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX locations_gist_idx ON areas USING gist (the_geom);


--
-- Name: reported_cases_gist_idx; Type: INDEX; Schema: public; Owner: admin; Tablespace: 
--

CREATE INDEX reported_cases_gist_idx ON cases USING gist (the_geom);


--
-- Name: Valid Attribute; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY case_type_attributes
    ADD CONSTRAINT "Valid Attribute" FOREIGN KEY (attribute_id) REFERENCES attributes(attribute_id) ON DELETE RESTRICT;


--
-- Name: Valid Type; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY case_type_attributes
    ADD CONSTRAINT "Valid Type" FOREIGN KEY (case_type_id) REFERENCES case_types(case_type_id) ON DELETE RESTRICT;


--
-- Name: locations_type_ref_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY areas
    ADD CONSTRAINT locations_type_ref_fkey FOREIGN KEY (area_type_id) REFERENCES area_types(area_type_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: reported_cases_agent_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY cases
    ADD CONSTRAINT reported_cases_agent_type_fkey FOREIGN KEY (case_type_id) REFERENCES case_types(case_type_id);


--
-- Name: user_roles_username_fkey1; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_username_fkey1 FOREIGN KEY (username) REFERENCES users(username) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--