/*
 * Create the ferryman schema
*/
DROP SCHEMA IF EXISTS ferryman CASCADE;

CREATE SCHEMA ferryman;

/**
  * Create function to automatically update updated_at column
 */

CREATE OR REPLACE FUNCTION ferryman.update_last_modified_at_column() 
RETURNS TRIGGER AS $$
BEGIN
    NEW.last_modified_at = now();
    RETURN NEW; 
END;
$$ language 'plpgsql';
