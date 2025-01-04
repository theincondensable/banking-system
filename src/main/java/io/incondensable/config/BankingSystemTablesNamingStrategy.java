package io.incondensable.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @author abbas
 */
public class BankingSystemTablesNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        String columnName = logicalName.getText();
        columnName = columnName.replace(columnName.charAt(0), Character.toLowerCase(columnName.toCharArray()[0]));
        StringBuilder columnNameWithUnderscore = new StringBuilder();
        char[] characters = columnName.toCharArray();

        for (char character : characters) {
            if (Character.isUpperCase(character))
                columnNameWithUnderscore.append('_');
            columnNameWithUnderscore.append(Character.toUpperCase(character));
        }
        final String t = "T_" + columnNameWithUnderscore;
        return new Identifier(t, logicalName.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        final String columnName = logicalName.getText();
        if (columnName.contains("FK"))
            return new Identifier(columnName, logicalName.isQuoted());

        StringBuilder columnNameWithUnderscore = new StringBuilder();
        char[] characters = columnName.toCharArray();

        for (char character : characters) {
            if (Character.isUpperCase(character))
                columnNameWithUnderscore.append('_');
            columnNameWithUnderscore.append(Character.toUpperCase(character));
        }

        final String t = "C_" + columnNameWithUnderscore;
        return new Identifier(t, logicalName.isQuoted());
    }

}
