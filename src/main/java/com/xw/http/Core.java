package com.xw.http;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.mina.http.api.*;
//import org.apache.mina.http.HttpRequestImpl;
/**
 * Author: junjie
 * Date: 3/12/15.
 */
public final class Core {
    private static final Logger _l = LogManager.getLogger(Core.class);

    private Core() {
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            _help();
            return;
        }

        final LongOpt[] opts = new LongOpt[]{
                new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
                new LongOpt("url", LongOpt.OPTIONAL_ARGUMENT, null, 'u')
        };

        final Getopt g = new Getopt(A.NAME, args, "hu:;", opts);
        g.setOpterr(true);
        int c;
        final Options options = new Options();

        while ((c = g.getopt()) != -1)
            switch (c) {
                case 'u':
                    options.url(g.getOptarg());
                    break;
                case 'h':
                    _help();
                    break;
                case ':':
                    _help(String.format("u need specify an argument for option:%s",
                            g.getOptopt()));
                    break;
                case '?':
                    _help(String.format("the option:%s is invalid",
                            g.getOptopt()));
                default:
                    _help(String.format("cli-parser return:%s", c));
                    break;
            }

        _request(options);
    }

    private static final void _request(final Options options) {
        _l.info("OPTIONS:\n=========================");
        _l.info(options);
        _l.info("RESPONSE:\n=========================");
        _l.info(String.format("<Tid:%s>", H.tid()));

//        HttpRequest req = new HttpRequestImpl();
    }

    private static final void _help() {
        _help(null);
    }

    private static final void _help(final String m) {
        if (null != m) {
            _l.info(m);
        }
        _l.info(String.format("usage: %s [-h|--help] [-u|--url]", A.NAME));
        _l.info("\t--url: specify the http url");
        System.exit(0);
    }
}
