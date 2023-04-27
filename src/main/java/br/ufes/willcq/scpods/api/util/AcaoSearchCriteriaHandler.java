package br.ufes.willcq.scpods.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AcaoSearchCriteriaHandler {

    public List<SearchCriteria> handle( String search ) {
        List<SearchCriteria> params = new ArrayList<>();

        if( search != null ) {

            Pattern pattern = Pattern.compile( "(\\w+?)([:<>])(\\w+?);", Pattern.UNICODE_CHARACTER_CLASS );
            Matcher matcher = pattern.matcher( search + ";" );

            while( matcher.find() ) {
                params.add( new SearchCriteria( matcher.group( 1 ), matcher.group( 2 ), matcher.group( 3 ) ) );
            }
        }
        return params;
    }

}
