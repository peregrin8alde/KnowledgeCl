package myapps;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Option.Builder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;


public class App {

    private static String url;
    private static String token;
    private static String mode;
    private static String title;
    private static String tags;
    private static String fileName;
    
    public static void main( String[] args ) {
        Option optUrl = Option.builder("url").longOpt("url")
                                  .hasArg()
                                  .required()
                                  .desc("use given Knowledge service URL")
                                  .build();
        Option optToken = Option.builder("token").longOpt("token")
                                  .hasArg()
                                  .required()
                                  .desc("use given Access Token")
                                  .build();
        Option optMode = Option.builder("mode").longOpt("mode")
                                  .hasArg()
                                  .required()
                                  .desc("use given mode")
                                  .build();
        Option optTitle = Option.builder("title").longOpt("title")
                                  .hasArg()
                                  .desc("use given title")
                                  .build();
        Option optTags = Option.builder("tags").longOpt("tags")
                                  .hasArg()
                                  .desc("use given tags")
                                  .build();
        Option optFileName = Option.builder("file").longOpt("file")
                                  .hasArg()
                                  .desc("use given Local Markdown file name")
                                  .build();
                                
        Options options = new Options();
        options.addOption(optUrl);
        options.addOption(optToken);
        options.addOption(optMode);
        options.addOption(optTitle);
        options.addOption(optTags);
        options.addOption(optFileName);

        // create the parser
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse( options, args );

            // set App fields
            if( line.hasOption( "url" ) ) {
                url = line.getOptionValue( "url" );
            }
            if( line.hasOption( "token" ) ) {
                token = line.getOptionValue( "token" );
            }
            if( line.hasOption( "mode" ) ) {
                mode = line.getOptionValue( "mode" );
            }
            if( line.hasOption( "title" ) ) {
                title = line.getOptionValue( "title" );
            }
            if( line.hasOption( "tags" ) ) {
                tags = line.getOptionValue( "tags" );
            }
            if( line.hasOption( "file" ) ) {
                fileName = line.getOptionValue( "file" );
            }
    
            KnowledgeApiCl cl = new KnowledgeApiCl(url, token);
            
            switch (mode) {
                case "POST":
                    cl.postKnowledge(title, tags, fileName);
                    break;
            }
        }
        catch( ParseException exp ) {
            // oops, something went wrong
            System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
        }
        
    }

}
